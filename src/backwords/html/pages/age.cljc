(ns backwords.html.pages.age
  (:require [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]
            [backwords.html.util :as util]
            [backwords.html.db :as db]
            [backwords.html.transition :as transition]
            [backwords.html.wrappers.page :as page]
            [cljc.java-time.local-date :as local-date]
            [cljc.java-time.temporal.chrono-unit :as chrono-unit]))

(defn- safe-date-parse [date-str]
  (try
    (local-date/parse date-str)
    (catch #?(:clj Exception :cljs :default) e
      nil)))

(defn- age-in-units [birthday today units]
  (let [units (case units
                "days"   chrono-unit/days
                "months" chrono-unit/months
                "years"  chrono-unit/years)]
    (chrono-unit/between units birthday today)))

(defn- select-units [{:keys [on-change] :as opts}]
  [:select.form-select.bg-inherit.text-shadow-inherit.border-transparent.pl-0.pr-7.py-0.-mr-2
   (assoc opts :on-change #(on-change (.. % -target -value)))
   [:option {:value "days"} "days"]
   [:option {:value "months"} "months"]
   [:option {:value "years"} "years"]])

(defn- you-are [name]
  (if name
    (str name ", you're")
    "You're"))

(defn- you-will-be [name]
  (if name
    (str name ", you'll be")
    "You'll be"))

(defn- random-link []
  [:a.block.mt-20.focus:underline.text-sm
   {:href (util/href :route/random)}
   "Random"])

(defn page [{:keys [path query]}]
  (let [{:keys [year month day units]} path

        date-str (str year "-" month "-" day)
        date     (safe-date-parse date-str)]
    (if-not date
      [page/article-wrapper
       [page/article
        [:p "Sorry, don't understand " date-str " as a date."]
        [random-link]]]
      (let [visit-units         #(util/visit [:route/age (assoc path :units %) query])
            today               (db/today)
            age                 (age-in-units date today units)
            digi-age            (diginum/from-int age)
            digi-palindrome-age (palindrome/next digi-age)
            palindrome-age      (diginum/to-int digi-palindrome-age)
            countdown           (- palindrome-age age)
            is-palindrome?      (zero? countdown)]
        [transition/palindrome {:in is-palindrome?}
         [page/article-wrapper
          [page/article
           (if is-palindrome?
             [:div
              [:p (you-are (:name query))]
              [:p
               [util/palindrome-span digi-palindrome-age]
               " "
               [select-units {:value units :on-change visit-units}]
               " old!"]]
             [:div
              [:p (you-will-be (:name query))]
              [:p
               [util/palindrome-span digi-palindrome-age]
               " "
               [select-units {:value units :on-change visit-units}]
               " old"]
              [:p
               "in "
               [util/digi-span (diginum/from-int countdown)]
               " "
               units]])
           [random-link]]]]))))
