(ns v3gaverse.server.api
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.content-negotiation :as conneg]
            [io.pedestal.http.ring-middlewares :as middlewares]
            [io.pedestal.http.params :as params]

            [portal.api :as p]))




(defn home-page [_]
  {:status 200
   :header {"content-type" "text/html"}
   :body "Howdie, v3ga!"})



(def common-interceptors [(body-params/body-params) http/html-body])
(def supported-types ["text/html" "application/edn" "application/json" "text/plain"])
(def content-negotiation-interceptor (conneg/negotiate-content supported-types))

(def routes
  #{["/" :get (conj common-interceptors `home-page)]
    })
