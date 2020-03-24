(ns backwords.html.pages.random
  (:require [backwords.html.pages.after :as pages.after]))

(defn page [_params]
  [pages.after/page (pages.after/small-rand)])
