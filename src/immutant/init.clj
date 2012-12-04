(ns immutant.init
  (:require immutant.web)
  (:use [ring.middleware.resource :only [wrap-resource]]
        [ring.util.response :only [redirect]]))

(defn handler [request]
  (redirect "/index.html"))

(immutant.web/start (wrap-resource handler "public"))
