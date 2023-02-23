(ns v3gaverse.server.ui.web
  (:require [ctmx.core :refer :all]
            
            [hiccup.page :refer [html5 include-css include-js]]))



(defn header []
  [:div#header
   [:div#logo [:img {:src "images/v3gaverse-logo.png"}]] 
   [:div#menu (str  "Search bar, login etc will go here")] 
   [:div#menu2 
    [:ul  
     [:a {:href "" }
      [:li "Books"]]
     [:a {:href ""}
      [:li "Stacks"]]
     [:a {:href ""}
      [:li "Where Am I?"]]]]])

(defn main []
  [:div#main
   [:div#content-menu
    [:ul.date-menu
     [:li [:a {:href "/"} "Jam"]]
     [:li [:a {:href "/"} "Feb "]]
     [:li [:a {:href "/"} "Mar"]]
     [:li [:a {:href "/"} "Apr"]]
     [:li [:a {:href ""} "May"]]
     [:li [:a {:href ""} "June"]]
     [:li [:a {:href ""} "July"]]
     [:li [:a {:href ""} "Aug"]]
     [:li [:a {:href ""} "Sept"]]
     [:li [:a {:href ""} "Oct"]]
     [:li [:a {:href ""} "Nov"]]
     [:li [:a {:href ""} "Dec"]]]]

   [:div#content
    [:div#timeline (str "timeline" )]]
   [:div#sidebar (str  "sidebar  ")]])

(defn page
  "Page Structure"
  [opts & children]
  (let [{:keys [title page]
         :or {title "v3gaverse"
              page :other}} opts]
    (html5
     [:head
      [:title title]
      (include-css "sass/style.css")]
     [:body
      [:div.wrapper
       (header)
       (main)
       
       ]])))





;;     [:div {:class "timeline-block timeline-iota-block"}
;;      [:div {:class "iota"}
;;       [:span {:class "iota-date"} "Today"]
;;       [:span {:class "iota-post"} "Random thought for the day. lets just write a bit more to see how it handles space.  I think this should work ok though."]]"<!-- end timeline-iota-block -->" ]
;;     [:div {:class "timeline-block timeline-stack-block"}
;;      [:div {:class "stack-info"}
;;       [:div {:class "timeline-stack-name"} "Political Circus"]"<!-- end timeline-stack-name -->"  
;;       [:div {:class "timeline-stack-date"} "Two days ago"]"<!-- end timeline-stack-date -->"  
;;       [:div {:class "timeline-stack-title"} "Title of latest post"]"<!-- end timeline-stack-title -->" ]"<!-- end stack-info -->"  
;;      [:div {:class "timeline-stack-preview"}
;;       [:div {:class "timeline-last-activity-on-stack"} "Isn&#39;t this funny that as we progress the same issues in the world continue with very little change other than updating the mask we decide to put on for the century? ..."]"<!-- end timeline-last-activity-on-stack -->"  
;;       [:div {:class "stack-more"}
;;        [:a {:href }
;;         [:span {:class "stack-more-block"} "More..."]]]
;;       [:ul {:class "stack-activity"}
;;        [:li 
;;         [:a {:href } "Thoughts on presidents speech in poland"]]
;;        [:li 
;;         [:a {:href } "Would we really start WWIII? What&#39;s the point?"]]
;;        [:li 
;;         [:a {:href } "I&#39;m done..."]]]]"<!-- end timeline-stack-preview -->" ]"<!-- end timeline-stack-block -->"  
;;     [:div {:class "timeline-block timeline-iota-block"}
;;      [:div {:class "iota"}
;;       [:span {:class "iota-date"} "6 days ago"]
;;       [:span {:class "iota-post"} "I think this will work well"]]"<!-- end timeline-iota-block -->" ]
;;     [:div {:class "timeline-block timeline-iota-block"}
;;      [:div {:class "iota"}
;;       [:span {:class "iota-date"} "22 days agos"]
;;       [:span {:class "iota-post"} "One more just to fill up some space"]]"<!-- end timeline-iota-block -->" ]]"<!-- end timeline -->" ]"<!-- end content -->"  
;;   [:div {:id "sidebar"} "Sidebar"]"<!-- end sidebar -->" ]"<!-- end content -->" ]"<!-- end wrapper -->" 
