(ns quaxt.fulstk.core
  (:require
    [yada.yada :as yada]
    [integrant.core :as ig]
    [next.jdbc :as jdbc]
    [next.jdbc.connection :as connection])
  (:import (com.zaxxer.hikari HikariDataSource)))

(defn string-resource
  [x]
  (yada/as-resource x))

(defn db-test[db-pool]
  (with-open [^HikariDataSource ds db-pool]
    (jdbc/execute! ds ["select 1"])))

(defn routes
  "make routes"
  [index db-pool resources]
  [""
   [["/" index]
    ["/hello" (string-resource (str (db-test db-pool)))]
    ["" resources]]])

(defmethod ig/init-key ::routes
  [_ [index;
      db-pool
      resources]]
  (routes index db-pool resources))

(defmethod ig/init-key ::db-pool
  [_ db-spec]
  (connection/->pool HikariDataSource db-spec))

(defmethod ig/halt-key! ::db-pool [_ db-pool]
  (.close db-pool))

