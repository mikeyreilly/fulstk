(ns ^:figwheel-hooks quaxt.cstest.frontend.main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [clojure.string :as str]))

;; (js/console.log "Hello, borld")

;; ;; This is called once
;; (defonce init
;;   (do (set! (.-innerHTML (js/document.getElementById "app"))
;;             "<p>Loaded cstest!</p>
;;             <p>Edit <strong><code>src/quaxt/cstest/frontend/main.cljs</code></strong> to change this message.</p>")
;;       true))

;; ;; This is called every time you make a code change
;; (defn ^:after-load reload []
;;   (set! (.-innerText (js/document.getElementById "app")) "snot Reloaded cstest!"))


(defonce timer (r/atom (js/Date.)))

(defonce time-color (r/atom "#f34"))

(defonce time-updater (js/setInterval
                       #(reset! timer (js/Date.)) 1000))

(defn greeting [message]
  [:h1 message])

(defn clock []
  (let [time-str (-> @timer .toTimeString (str/split " ") first)]
    [:div.example-clock
     {:style {:color @time-color}}
     time-str]))

(defn color-input []
  [:div.color-input
   "Time color: "
   [:input {:type "text"
            :value @time-color
            :on-change #(reset! time-color (-> % .-target .-value))}]])

(defn simple-example []
  [:div
   [greeting "Hello world, it is now"]
   [clock]
   [color-input]])

(rdom/render [simple-example] (js/document.getElementById "app"))
