(ns mintos-hw.middleware
  (:require
    [ring.middleware.gzip :refer [wrap-gzip]]
    [ring.middleware.defaults :refer [site-defaults wrap-defaults]]))

(def middleware
  [#(wrap-defaults % site-defaults)
   wrap-gzip])
