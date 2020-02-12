(ns mintos-hw.prod
  (:require [mintos-hw.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
