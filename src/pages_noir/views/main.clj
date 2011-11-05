(ns pages-noir.views.main
  (:require [pages-noir.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.form-helpers
        hiccup.core
        hiccup.page-helpers))

;; Static file definitions
(def screen-css {:type :css :filename "screen.css"})
(def print-css {:type :css :filename "print.css"})
  
;; Static/media files info
(def static-media-url "http://media.yukmarks.com")
(def css-path (str static-media-url "/css/blueprint"))
(def js-path (str static-media-url "/js"))
(def jquery-url "http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js")
(def includes [{:jquery (include-js jquery-url)
                :screen (include-css (make-static-path screen-css))
                :print (include-css (make-static-path print-css))}])

(defn make-static-path [fmap]
  "Return an absolute path to static media based on filetype."
  (apply str (interpose "/" [({:css css-path :js js-path} (:type fmap))
                             (:filename fmap)])))

;; Header links for logged-in users
(def acct-links [{:url "/note/add" :text "add note"}
                 {:url "/quote/add" :text "add quote"}
                 {:url "/url/add" :text "add url"}
                 {:url "/rss_feed/import" :text "import rss feed"}
                 {:url "/file/import" :text "import from file"}
                 {:url "/export" :text "export your data"}])

(defpartial build-head [incls]
  [:head
   [:title "Pages Noir"]]
  (map #(get includes %) incls))

;; HTML widget for a single saved item
(defpartial saved-item [{:keys [url text class descrip obj-id]}]
  [:li
   [:p
    (link-to {:class "url" :target "_blank"} url text)]
   [:div {:id "tags"}
    [:p
     [:font {:size "2"}
      "(tags: )"]]]
   [:div {:id descrip}
    (link-to {:class (str "edit_url_" :obj-id)
              :target "_blank"}
             "/edit_link" "Edit")]])


