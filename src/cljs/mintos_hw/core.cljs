(ns mintos-hw.core
  (:require
    [reagent.core :as reagent :refer [atom]]
    [clojure.string :as string]))

(def currencies ["eur" "pln" "gel" "dkk" "czk" "gbp" "sek" "usd" "rub"])

(defn append-class [& args]
  (-> args
      flatten
      vec))

(defn svg-x [class]
  [:svg {:class   class
         :xmlns   "http://www.w3.org/2000/svg"
         :viewBox "0 0 386.667 386.667"}
   [:path {:d "m386.667 45.564-45.564-45.564-147.77 147.769-147.769-147.769-45.564 45.564 147.769 147.769-147.769 147.77 45.564 45.564 147.769-147.769 147.769 147.769 45.564-45.564-147.768-147.77z"}]])

(defn enter-or-space [e]
  (let [key-code (.-which e)]
    (or (= key-code 13)
        (= key-code 32))))

(defn remove-currency [on-click]
  [:div.remove-currency
   {:on-click    on-click
    ;; adds keyboard support
    :tabIndex    0
    :on-key-down (fn [e]
                   (when (enter-or-space e)
                     (on-click)))}
   [svg-x :remove-currency__icon]])

(defn checkbox [selected?]
  [:div.checkbox
   (when selected?
     [svg-x :checkbox__icon])])

(defn currency-pill [currency selected? on-click]
  [:div {:class       (cond-> [:currency-pill]
                              selected? (append-class :currency-pill--selected))
         :tabIndex    0
         :on-key-down (fn [e]
                        (when (enter-or-space e)
                          (on-click)))
         :on-click    on-click}
   [checkbox selected?]
   [:span {:style {:margin-left 4}}
    (string/upper-case currency)]])

(defn selected-currency-pill [currency on-click]
  [:div.selected-currency-pill
   (string/upper-case currency)
   [remove-currency on-click]])

(defn selected-currencies [selected-]
  (when-not (empty? @selected-)
    [:div.currency-list-wrapper
     {:style {:margin-bottom 16}}
     (doall
       (for [currency @selected-]
         (let [selected? (contains? @selected- currency)
               on-click #(if selected?
                           (reset! selected- (disj @selected- currency))
                           (reset! selected- (conj @selected- currency)))]
           ^{:key (random-uuid)}
           [selected-currency-pill currency on-click])))]))

(defn available-currencies [selected-]
  [:div.currency-list-wrapper
   (doall
     (for [currency currencies]
       (let [selected? (contains? @selected- currency)
             on-click #(if selected?
                         (reset! selected- (disj @selected- currency))
                         (reset! selected- (conj @selected- currency)))]
         ^{:key (random-uuid)}
         [currency-pill currency selected? on-click])))])

(defn cash-money-business []
  ;; stores the state of the "app"
  (let [selected- (reagent/atom #{})]
    (fn []
      [:section.cash-money-business
       [selected-currencies selected-]
       [available-currencies selected-]])))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [cash-money-business] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
