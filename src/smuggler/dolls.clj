(ns smuggler.dolls)

(defn total-weight [dolls]
  (reduce + (map :weight dolls)))

(defn- add-if-fits [w included doll]
  (let [avail-weight (- w (total-weight included))]
    (if (<= (:weight doll) avail-weight)
      (conj included doll)
      included)))

(defn- optimize-value [w dolls]
  (reduce (partial add-if-fits w) #{} dolls))

(defn- add-value-to-weight [doll]
  (assoc doll :value-to-weight (/ (:value doll) (:weight doll))))

(defn pack-bag [w dolls]
  (->> dolls
    (map add-value-to-weight)
    (sort-by :value-to-weight)
    (reverse)
    (optimize-value w)
    (map #(dissoc % :value-to-weight))
    (set)))
