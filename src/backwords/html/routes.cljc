(ns backwords.html.routes
  (:require [reitit.frontend :as rf]
            [backwords.html.db :as db]
            [backwords.html.pages.random :as pages.random]
            [reitit.frontend.easy :as rfe]))

(def ^:private routes
  [""
   ["/" {:name :route/home
         :view pages.random/page}]
   ["/random" {:name :route/random
               :view pages.random/page}]])

(def ^:private router
  (rf/router routes))

(defn ^:dev/after-load initialize
  []
  (rfe/start! router db/save-route {:use-fragment true}))
