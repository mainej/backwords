(ns backwords.html.pages.age
  (:require [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]
            [backwords.html.util :as util]
            [backwords.html.db :as db]
            [cljc.java-time.local-date :as local-date]
            [cljc.java-time.temporal.chrono-unit :as chrono-unit]))

(defn safe-date-parse [date-str]
  (try
    (local-date/parse date-str)
    (catch #?(:clj Exception :cljs :default) e
        nil)))

(defn age-in-units [birthday today units]
  (let [units (case units
                "days"   chrono-unit/days
                "months" chrono-unit/months
                "years"  chrono-unit/years)]
    (chrono-unit/between units birthday today)))

(defn select-units [units {:keys [on-change]}]
  [:select
   {:on-change #(on-change (.. % -target -value))}
   [:option {:value "days" :selected (= units "days")} "days"]
   [:option {:value "months" :selected (= units "months")} "months"]
   [:option {:value "years" :selected (= units "years")} "years"]])

(defn you-are [name]
  (if name
    (str name ", you're")
    "You're"))

(defn you-will-be [name]
  (if name
    (str name ", you'll be")
    "You'll be"))

(defn page [{:keys [path query]}]
  (let [{:keys [year month day units]} path

        date-str (str year "-" month "-" day)
        date     (safe-date-parse date-str)]
    [:div.font-mono.p-8.max-w-xs.m-auto.text-right.flex.flex-col.justify-between.min-h-screen
     (if-not date
       [:p "Sorry, don't understand " date-str " as a date."]
       (let [visit-units         #(util/visit [:route/age (assoc path :units %) query])
             today               (db/today)
             age                 (age-in-units date today units)
             digi-age            (diginum/from-int age)
             palindrome-digi-age (palindrome/next digi-age)
             palindrome-age      (diginum/to-int palindrome-digi-age)
             countdown           (- palindrome-age age)]
         (if (zero? countdown)
           [:div.text-blue-500
            [:p (you-are (:name query))]
            [:p
             [util/palindrome-span palindrome-digi-age]
             " "
             [select-units units {:on-change visit-units}]]
            [:p "old!"]]
           (let [digi-countdown (diginum/from-int countdown)]
             [:div
              [:p (you-will-be (:name query))]
              [:p
               [util/palindrome-span palindrome-digi-age]
               " "
               [select-units units {:on-change visit-units}]]
              [:p "old in"]
              [:p
               [util/digi-span digi-countdown]
               " "
               units]]))))]))
