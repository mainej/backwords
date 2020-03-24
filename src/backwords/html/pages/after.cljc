(ns backwords.html.pages.after
  (:require [backwords.html.util :as util]
            [backwords.html.transition :as transition]
            [backwords.html.wrappers.page :as page]
            [backwords.html.components.palindrome :as palindrome]))

(defn random-link []
  [:a.block.mt-20.focus:underline.text-sm
   {:href (util/href :route/random)}
   "Random"])

(defn page [params]
  (let [n (util/parse-int (:n (:path params)))

        {:keys [is-palindrome?] :as after-state} (palindrome/after-state n)]
    [transition/palindrome {:in is-palindrome?}
     [page/article-wrapper
      [page/article
       [palindrome/after after-state]
       [random-link]]]]))
