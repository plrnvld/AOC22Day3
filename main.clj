(require '[clojure.java.io :as jio]
         '[clojure.set :as set])

(defn CharScore [c]     
    (let [intVal (int c)] 
        (if (<= intVal (int \Z))
            (+ (- intVal (int \A)) 27)
            (+ (- intVal (int \a)) 1))))

(defn FindBadge 
    ([triple] 
        (let [head (first triple) 
              tail (next triple)] 
            (FindBadge head (first tail) (first (next tail)))))
    ([line1 line2 line3]
        (set/intersection (set line1) (set line2) (set line3))))

(defn BadgeScore 
    [group] (reduce + (map CharScore (FindBadge group))))

(defn CalcBadges []
    (with-open [rdr (jio/reader "Input.txt")]
        (let [triples (partition 3 (line-seq rdr))]
            (println (reduce + (map BadgeScore triples))))))

(CalcBadges)