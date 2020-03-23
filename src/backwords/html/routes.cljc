(ns backwords.html.routes
  (:require [reitit.frontend :as rf]
            [backwords.html.db :as db]
            [backwords.html.pages.random :as pages.random]
            [backwords.html.pages.after :as pages.after]
            [backwords.html.pages.age :as pages.age]
            [reitit.frontend.easy :as rfe]))

(def ^:private routes
  [""
   ["/"
    {:name :route/home
     :view pages.random/page}]
   ["/random"
    {:name :route/random
     :view pages.random/page}]
   ["/after/:n"
    {:name :route/after
     :view pages.after/page}]
   ["/age/in/:units/since/:year/:month/:day"
    {:name :route/age
     :view pages.age/page}]
   ])

(def ^:private router
  (rf/router routes))

(defn ^:dev/after-load initialize
  []
  (rfe/start! router db/save-route {:use-fragment true}))
