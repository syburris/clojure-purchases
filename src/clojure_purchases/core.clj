(ns clojure-purchases.core
  (:require [clojure.string :as str])
  (:gen-class))

(def file-name "purchases.csv")

(defn -main [& args]
  (print "Type a purchase category: ")
  (let [purchases (str/split-lines(slurp file-name))
        purchases (map (fn [line]
                         (str/split line #","))
                    purchases)
        header (first purchases)
        purchases (rest purchases)
        purchases (map (fn [line]
                         (zipmap header line))
                    purchases)
        purchase-category (read-line)
        purchases (filter (fn [line]
                            (= (get line "category") purchase-category))
                    purchases)
        file-text (pr-str purchases)]
    (spit "filtered_purchases.edn" file-text)))

