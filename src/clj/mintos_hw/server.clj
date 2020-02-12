(ns mintos-hw.server
  (:require
    [mintos-hw.handler :refer [app]]
    [config.core :refer [env]]
    [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn- add-gzip-handler [server]
  (.setHandler server
               (doto (GzipHandler.)
                 (.setIncludedMimeTypes (into-array ["text/css"
                                                     "text/plain"
                                                     "text/javascript"
                                                     "application/javascript"
                                                     "application/json"
                                                     "image/svg+xml"]))
                 (.setMinGzipSize 1024)
                 (.setHandler (.getHandler server)))))

(defn -main [& args]
  (let [port (or (env :port) 3000)]
    (run-jetty app {:port         port
                    :configurator add-gzip-handler
                    :join?        false})))
