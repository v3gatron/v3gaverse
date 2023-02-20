(ns v3gaverse.server.core
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]

            [v3gaverse.server.api :as api]))



(def service-map
  {:env                 :dev
   ::http/resource-path "/public"
   ::http/type          :jetty
   ::http/port          8080})


#_(defonce runnable-service (http/create-server service-map))

(defn run-dev
  "The entry-point for 'lein run-dev'"
  [& args]
  (println "\nCreating your [DEV] server...")
  (-> service-map ;; start with production configuration
      (merge {:env :dev
              ;; do not block thread that starts web server
              ::http/join? false
              ;; Routes can be a function that resolve routes,
              ;;  we can use this to set the routes to be reloadable
              ::http/routes #(route/expand-routes (deref #'api/routes))
              ;; all origins are allowed in dev mode
              ::http/allowed-origins {:creds true :allowed-origins (constantly true)}
              ;; Content Security Policy (CSP) is mostly turned off in dev mode
              ::http/secure-headers {:content-security-policy-settings {:object-src "'none'"}}})
      ;; Wire up interceptor chains
      http/default-interceptors
      http/dev-interceptors
      http/create-server
      http/start))

#_(def serv (run-dev))
#_(http/stop serv)


