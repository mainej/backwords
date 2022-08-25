(ns backwords.html.pages.after
  (:require [backwords.html.util :as util]
            [backwords.html.transition :as transition]
            [backwords.html.wrappers.page :as page]
            [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]))

(defn- expt [n e]
  #?(:clj (Math/pow n e)
     :cljs (js/Math.pow n e)))

(defn small-rand []
  (+ 10 (rand-int (expt 10 (inc (rand-int 7))))))

(defn page [n]
  (let [digi-n         (diginum/from-int n)
        digi-p         (palindrome/next digi-n)
        is-palindrome? (= digi-n digi-p)]
    [transition/palindrome {:in is-palindrome?}
     [page/article-wrapper
      [page/article
       [:div.leading-tight
        (if is-palindrome?
          [:div
           [:p [util/palindrome-span digi-p]]
           [:p.my-4 "is a palindrome!"]]
          [:div
           [:p [util/palindrome-span digi-p]]
           [:p.my-4 "follows" [:br]
            [util/digi-span digi-n]]])]
       [:a.inline-block.mt-8.px-4.py-2.border
        {:href  (util/href :route/after {:n (small-rand)})
         :class (if is-palindrome? :border-white :border-gray-900)}
        "Give me another!"]]]]))

(defn page-from-route [params]
  [page (util/parse-int (:n (:path params)))])
