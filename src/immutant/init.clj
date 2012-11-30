(ns immutant.init
  (:require immutant.web))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<h1>Hello from OpenShift!</h1>"})

(immutant.web/start handler)