(ns clojure.examples.aoc22day3
   (:gen-class))

(require '[clojure.string :as str])
(require '[clojure.java.io :as jio])

(defn FirstHalf [line] 
    (let [len (count line)] 
        (subs line 0 (/ len 2))))

(defn SecondHalf [line] 
    (let [len (count line)] 
        (subs line (/ len 2))))

;; Read all the lines one by one
(defn ReadLines []
(with-open [rdr (jio/reader "Example.txt")]
  (doseq [line (line-seq rdr)]
    (println 
     (str 
      (FirstHalf line)
      "-"
      (SecondHalf line)
      )))))
(ReadLines)