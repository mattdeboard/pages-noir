(ns pages-noir.server
  (:require [noir.server :as server]))

(server/load-views "src/pages_noir/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'pages-noir})))

