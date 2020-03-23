(ns backwords.html.pages.after
  (:require [backwords.html.util :as util]
            [backwords.html.components.palindrome :as palindrome]))

(defn page [params]
  [:div.py-6
   [:div.font-mono.p-8.max-w-xs.m-auto.text-right
    [palindrome/after (util/parse-int (:n (:path params)))]]])
