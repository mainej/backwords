(ns backwords.html.live
  (:require [backwords.html.routes :as routes]
            [backwords.html.db :as db]
            [reagent.dom]
            ))

(def debug?
  ^boolean goog.DEBUG)

(defn dev-setup []
  (when debug?
    (enable-console-print!)
    (println "dev mode")))

(defn main-view []
  (let [route (db/active-route)]
    [:div.min-w-screen.font-mono
     [:main.min-h-screen
      [:div.max-w-7xl.mx-auto
       (when-let [view (:view (:data route))]
         [view (:parameters route)])]]]))

(defn ^:export mount-root []
  (reagent.dom/render [main-view]
                      (.getElementById js/document "app")))

(defn ^:export init []
  (routes/initialize)
  (dev-setup)
  (mount-root))
