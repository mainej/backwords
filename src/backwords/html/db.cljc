(ns backwords.html.db
  (:require [reitit.frontend.controllers :as rfc]
            [cljc.java-time.local-date-time :as local-date-time]
            [reagent.core :as r]
            [reagent.ratom :as ra #?@(:cljs [:include-macros true])]))

(defonce !active-route (r/atom nil))

(defn active-route []
  (deref !active-route))

(defn save-route [new-match]
  (when new-match
    (swap! !active-route
           (fn [active-route]
             (let [controllers (rfc/apply-controllers (:controllers active-route) new-match)]
               (assoc new-match :controllers controllers))))))

(defonce !now (r/atom (local-date-time/now)))
(defn now [] @!now)
#?(:cljs
   (defonce time-updater (js/setInterval #(reset! !now (local-date-time/now)) 1000)))

(def !today (ra/reaction (local-date-time/to-local-date (now))))
(defn today [] @!today)
