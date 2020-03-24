(ns backwords.html.pages.random
  (:require [reagent.core :as r]
            [backwords.html.util :as util]
            [backwords.html.wrappers.page :as page]
            [backwords.html.transition :as transition]
            [backwords.html.components.palindrome :as palindrome]))

(defn- expt [n e]
  #?(:clj (Math/pow n e)
     :cljs (js/Math.pow n e)))

(defn- small-rand []
  (+ 10 (rand-int (expt 10 (inc (rand-int 7))))))

(def svg-opts
  {:viewBox "0 0 24 24"
   :xmlns   "http://www.w3.org/2000/svg"})

(def path-opts-lock
  {:d               "M13.8284 10.1716C12.2663 8.60948 9.73367 8.60948 8.17157 10.1716L4.17157 14.1716C2.60948 15.7337 2.60948 18.2663 4.17157 19.8284C5.73367 21.3905 8.26633 21.3905 9.82843 19.8284L10.93 18.7269M10.1716 13.8284C11.7337 15.3905 14.2663 15.3905 15.8284 13.8284L19.8284 9.82843C21.3905 8.26633 21.3905 5.73367 19.8284 4.17157C18.2663 2.60948 15.7337 2.60948 14.1716 4.17157L13.072 5.27118"
   :stroke-linecap  "round"
   :stroke-linejoin "round"})

(defn- permalink [n]
  [:a.block.mt-20
   {:href       (util/href :route/after {:n n})
    :aria-label "Permalink"}
   [:svg.h-4.w-4.fill-none.stroke-2.stroke-current.inline svg-opts
    [:title "Permalink"]
    [:path path-opts-lock]]])

(defn page* [{:keys [get-n change-n]}]
  (let [n (get-n)

        {:keys [is-palindrome?] :as after-state} (palindrome/after-state n)]
    [transition/palindrome {:in is-palindrome?}
     [page/article-wrapper
      [page/article
       [palindrome/after after-state]
       [:button.mt-8.px-4.py-2.border
        {:on-click change-n
         :class    (if is-palindrome? :border-white :border-gray-900)}
        "Give me another!"]
       [permalink n]]]]))

(defn local-state []
  (let [!n (r/atom (small-rand))]
    {:get-n    #(deref !n)
     :change-n #(reset! !n (small-rand))}))

(defn page [_params]
  [page* (local-state)])
