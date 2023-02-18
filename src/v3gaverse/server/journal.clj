(ns v3gaverse.server.journal)

;; TODO: lets go...
;; Posts, Iota and Notes

(defmulti post :post-type)
(def iota {:iota "This is the first iota"
           :create-at "Feb-18-2023"
           :view 9000})

