(ns backwords.html.wrappers.page)

(def article-wrapper :div.py-6.min-h-screen)
(def article :article.p-8.max-w-sm.m-auto.text-right.leading-tight)

(defn main [{:keys [data parameters]}]
  [:main.min-h-screen.mx-auto
   (when-let [view (:view data)]
     [view parameters])])

