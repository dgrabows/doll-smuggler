(ns smuggler.core)

(defn- add-value-to-weight [doll]
  (assoc doll :value-to-weight (/ (:value doll) (:weight doll))))

(defn total-weight [dolls]
  (reduce + (map :weight dolls)))

(defn- find-opt
  ([w dolls]
    (find-opt w dolls #{}))
  ([w dolls included]
    (let [remaining (- w (total-weight included))]
      (if (or (empty? dolls) (zero? remaining))
        included
        (find-opt
          w 
          (rest dolls) 
          (if (<= (:weight (first dolls)) remaining)
            (conj included (first dolls))
            included))))))

(defn opt-load [w dolls]
  (->> dolls
    (map add-value-to-weight)
    (sort-by :value-to-weight)
    (reverse)
    (find-opt w)
    (map #(dissoc % :value-to-weight))
    (set)))
