(ns backwords.html.pages.age
  (:require [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]
            [backwords.html.util :as util]
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

(defn page [{:keys [path query]}]
  (let [{:keys [year month day units]} path
        date-str                       (str year "-" month "-" day)]
    (if-let [date (safe-date-parse date-str)]
      (let [today               (local-date/now)
            age                 (age-in-units date today units)
            digi-age            (diginum/from-int age)
            palindrome-digi-age (palindrome/next digi-age)
            palindrome-age      (diginum/to-int palindrome-digi-age)
            countdown           (- palindrome-age age)]
        [:div.font-mono.p-8.max-w-xs.m-auto.text-right.flex.flex-col.justify-between.min-h-screen
         (if (zero? countdown)
           [:div.text-blue-500
            [:p (if-let [name (:name query)]
                  (str name ", you're")
                  "You're")]
            [:p
             [:span.font-bold.text-4xl [util/format-digi-n palindrome-digi-age]]
             " "
             [select-units units {:on-change #(util/visit [:route/age (assoc path :units %) query])}]]
            [:p "old!"]]
           (let [digi-countdown (diginum/from-int countdown)]
             [:div
              [:p (if-let [name (:name query)]
                    (str name ", you'll be")
                    "You'll be")]
              [:p
               [:span.font-bold.text-4xl [util/format-digi-n palindrome-digi-age]]
               " "
               [select-units units {:on-change #(util/visit [:route/age (assoc path :units %) query])}]]
              [:p "old in"]
              [:p
               [:span.font-bold.text-2xl [util/format-digi-n digi-countdown]]
               " "
               units]]))])
      [:div
       [:p "Sorry, don't understand " date-str " as a date."]])))
