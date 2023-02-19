(ns v3gaverse.server.journal
  (:require [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [next.jdbc.result-set :as rs]
            [next.jdbc.sql :as sql]
            [honey.sql :as honeysql]
            [honey.sql.helpers :as hh])
  (:import (com.zaxxer.hikari HikariDataSource)))


;; DONE: Add database
;; DONE: Look into DB pool but don't add it yet
;; TODO: bring over full CRUD for iota only
;; TODO: Spin up web server, whether thats pedestal or ring
;; TODO: Start common layout pieces that can be shared between client view and your view. Also HTMX?
;; TODO: Show iotas
;; I want to be able to just hop on and talk trash about news as well.
;; I should be able to autopost to twitter when a Note is created or a new `Stack` is created/updated
;; Posts, Iota and Notes


(def db-spec {:dbtype   "postgresql"
              :dbname   "saga"
              :user "v3ga"
              :password "orchidok"})

(def datasource (jdbc/get-datasource db-spec))

(defn start-connection-pool [db]
  (connection/->pool HikariDataSource db))

(defn stop-connection-pool [datasource]
  (.close datasource))

(def db-interceptor
  {:name ::db-interceptor
   :enter (fn [ctx]
            (let [db-conn (jdbc/with-options db-spec {})]
              (assoc ctx :db-conn db-conn)))})

(jdbc/execute! datasource
               ["create table if not exists iota(id SERIAL NOT NULL PRIMARY KEY,
                                                 post varchar(1000) NOT NULL,
                                                 created_at TIMESTAMP DEFAULT Now())"])


;; -x-- API ------------------------------

(defmulti create-post :type)

(defmethod create-post :iota [{:keys [post]}]
  (let [post post])
  (->  (hh/insert-into :iota)
       (hh/columns :post)
       (hh/values [[post]])))

                       


(defmethod create-post :note [{:keys [title post created-at format last-viewed]}]
  (hh/insert-into :note
                     :title :post :created_at :format :last_viewed
                     [[title post created-at format last-viewed]]))

(defn create-iota! [{:keys [post]}]
  (create-post {:type :iota :post post}))

(defn create-note [{:keys [id title post created-at format last-viewed]}]
  (create-post {:type :note :id id :title title :post post :created-at created-at :format format :last-viewed last-viewed}))




(defn db-query [sql]
  (jdbc/execute! datasource sql
                 {:return-keys true
                  :builder-fn rs/as-unqualified-maps}))

(defn db-query-one [sql]
  (jdbc/execute-one! datasource sql
                   {:return-keys true
                    :builder-fn rs/as-unqualified-maps}))



(defn query-one! [query]
  (-> (honeysql/format query)
      db-query-one))

(defn query! [query]
  (-> (honeysql/format query)
      db-query))



(query! (create-iota! {:post "this works now, good"}))

(def insert-iota
  {:name ::insert-iota
   :enter (fn [ctx]
            (let [id 1
                  post (-> ctx :request :json-params :post)
                  new-iota (create-iota id post)]
              (insert-iota! db/datasource new-iota)
              (created-response ctx new-iota)))})
