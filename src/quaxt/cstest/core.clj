(ns quaxt.cstest.core
  (:require
    [yada.yada :as yada]
    [integrant.core :as ig]))

(defn string-resource
  [x]
  (yada/as-resource x))

(defmethod ig/init-key ::string
  [_ x]
  (string-resource x))

(defn routes
  "make routes"
  [index string resources]
    [""
   [["/" index]
    ["/hello" string]
    ["" resources]]])

(defmethod ig/init-key ::routes
  [_ [index string resources]]
  (routes index string resources))
