(ns backwords.html.pages.after
  (:require [backwords.html.components.palindrome :as palindrome]))

(defn parse-int [s]
  #?(:clj (Integer/parseInt s)
     :cljs (js/parseInt s)))

(defn page [params]
  [:div.font-mono.p-8.max-w-xs.m-auto.text-right
   [palindrome/after (parse-int (:n (:path params)))]])
