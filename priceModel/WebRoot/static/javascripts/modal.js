/* ======================================================================== 
2  * Bootstrap: modal.js v3.2.0 
3  * http://getbootstrap.com/javascript/#modals 
4  * ======================================================================== 
5  * Copyright 2011-2014 Twitter, Inc. 
6  * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE) 
7  * ======================================================================== */ 
8 
 
9 
 
10 +function ($) { 
11   'use strict'; 
12 
 
13   // MODAL CLASS DEFINITION 
14   // ====================== 
15 
 
16   var Modal = function (element, options) { 
17     this.options        = options 
18     this.$body          = $(document.body) 
19     this.$element       = $(element) 
20     this.$backdrop      = 
21     this.isShown        = null 
22     this.scrollbarWidth = 0 
23 
 
24     if (this.options.remote) { 
25       this.$element 
26         .find('.modal-content') 
27         .load(this.options.remote, $.proxy(function () { 
28           this.$element.trigger('loaded.bs.modal') 
29         }, this)) 
30     } 
31   } 
32 
 
33   Modal.VERSION  = '3.2.0' 
34 
 
35   Modal.TRANSITION_DURATION = 300 
36   Modal.BACKDROP_TRANSITION_DURATION = 150 
37 
 
38   Modal.DEFAULTS = { 
39     backdrop: true, 
40     keyboard: true, 
41     show: true 
42   } 
43 
 
44   Modal.prototype.toggle = function (_relatedTarget) { 
45     return this.isShown ? this.hide() : this.show(_relatedTarget) 
46   } 
47 
 
48   Modal.prototype.show = function (_relatedTarget) { 
49     var that = this 
50     var e    = $.Event('show.bs.modal', { relatedTarget: _relatedTarget }) 
51 
 
52     this.$element.trigger(e) 
53 
 
54     if (this.isShown || e.isDefaultPrevented()) return 
55 
 
56     this.isShown = true 
57 
 
58     this.checkScrollbar() 
59     this.$body.addClass('modal-open') 
60 
 
61     this.setScrollbar() 
62     this.escape() 
63 
 
64     this.$element.on('click.dismiss.bs.modal', '[data-dismiss="modal"]', $.proxy(this.hide, this)) 
65 
 
66     this.backdrop(function () { 
67       var transition = $.support.transition && that.$element.hasClass('fade') 
68 
 
69       if (!that.$element.parent().length) { 
70         that.$element.appendTo(that.$body) // don't move modals dom position 
71       } 
72 
 
73       that.$element 
74         .show() 
75         .scrollTop(0) 
76 
 
77       if (transition) { 
78         that.$element[0].offsetWidth // force reflow 
79       } 
80 
 
81       that.$element 
82         .addClass('in') 
83         .attr('aria-hidden', false) 
84 
 
85       that.enforceFocus() 
86 
 
87       var e = $.Event('shown.bs.modal', { relatedTarget: _relatedTarget }) 
88 
 
89       transition ? 
90         that.$element.find('.modal-dialog') // wait for modal to slide in 
91           .one('bsTransitionEnd', function () { 
92             that.$element.trigger('focus').trigger(e) 
93           }) 
94           .emulateTransitionEnd(Modal.TRANSITION_DURATION) : 
95         that.$element.trigger('focus').trigger(e) 
96     }) 
97   } 
98 
 
99   Modal.prototype.hide = function (e) { 
100     if (e) e.preventDefault() 
101 
 
102     e = $.Event('hide.bs.modal') 
103 
 
104     this.$element.trigger(e) 
105 
 
106     if (!this.isShown || e.isDefaultPrevented()) return 
107 
 
108     this.isShown = false 
109 
 
110     this.$body.removeClass('modal-open') 
111 
 
112     this.resetScrollbar() 
113     this.escape() 
114 
 
115     $(document).off('focusin.bs.modal') 
116 
 
117     this.$element 
118       .removeClass('in') 
119       .attr('aria-hidden', true) 
120       .off('click.dismiss.bs.modal') 
121 
 
122     $.support.transition && this.$element.hasClass('fade') ? 
123       this.$element 
124         .one('bsTransitionEnd', $.proxy(this.hideModal, this)) 
125         .emulateTransitionEnd(Modal.TRANSITION_DURATION) : 
126       this.hideModal() 
127   } 
128 
 
129   Modal.prototype.enforceFocus = function () { 
130     $(document) 
131       .off('focusin.bs.modal') // guard against infinite focus loop 
132       .on('focusin.bs.modal', $.proxy(function (e) { 
133         if (this.$element[0] !== e.target && !this.$element.has(e.target).length) { 
134           this.$element.trigger('focus') 
135         } 
136       }, this)) 
137   } 
138 
 
139   Modal.prototype.escape = function () { 
140     if (this.isShown && this.options.keyboard) { 
141       this.$element.on('keydown.dismiss.bs.modal', $.proxy(function (e) { 
142         e.which == 27 && this.hide() 
143       }, this)) 
144     } else if (!this.isShown) { 
145       this.$element.off('keydown.dismiss.bs.modal') 
146     } 
147   } 
148 
 
149   Modal.prototype.hideModal = function () { 
150     var that = this 
151     this.$element.hide() 
152     this.backdrop(function () { 
153       that.$element.trigger('hidden.bs.modal') 
154     }) 
155   } 
156 
 
157   Modal.prototype.removeBackdrop = function () { 
158     this.$backdrop && this.$backdrop.remove() 
159     this.$backdrop = null 
160   } 
161 
 
162   Modal.prototype.backdrop = function (callback) { 
163     var that = this 
164     var animate = this.$element.hasClass('fade') ? 'fade' : '' 
165 
 
166     if (this.isShown && this.options.backdrop) { 
167       var doAnimate = $.support.transition && animate 
168 
 
169       this.$backdrop = $('<div class="modal-backdrop ' + animate + '" />') 
170         .appendTo(this.$body) 
171 
 
172       this.$element.on('click.dismiss.bs.modal', $.proxy(function (e) { 
173         if (e.target !== e.currentTarget) return 
174         this.options.backdrop == 'static' 
175           ? this.$element[0].focus.call(this.$element[0]) 
176           : this.hide.call(this) 
177       }, this)) 
178 
 
179       if (doAnimate) this.$backdrop[0].offsetWidth // force reflow 
180 
 
181       this.$backdrop.addClass('in') 
182 
 
183       if (!callback) return 
184 
 
185       doAnimate ? 
186         this.$backdrop 
187           .one('bsTransitionEnd', callback) 
188           .emulateTransitionEnd(Modal.BACKDROP_TRANSITION_DURATION) : 
189         callback() 
190 
 
191     } else if (!this.isShown && this.$backdrop) { 
192       this.$backdrop.removeClass('in') 
193 
 
194       var callbackRemove = function () { 
195         that.removeBackdrop() 
196         callback && callback() 
197       } 
198       $.support.transition && this.$element.hasClass('fade') ? 
199         this.$backdrop 
200           .one('bsTransitionEnd', callbackRemove) 
201           .emulateTransitionEnd(Modal.BACKDROP_TRANSITION_DURATION) : 
202         callbackRemove() 
203 
 
204     } else if (callback) { 
205       callback() 
206     } 
207   } 
208 
 
209   Modal.prototype.checkScrollbar = function () { 
210     this.scrollbarWidth = this.measureScrollbar() 
211   } 
212 
 
213   Modal.prototype.setScrollbar = function () { 
214     var bodyPad = parseInt((this.$body.css('padding-right') || 0), 10) 
215     if (this.scrollbarWidth) this.$body.css('padding-right', bodyPad + this.scrollbarWidth) 
216   } 
217 
 
218   Modal.prototype.resetScrollbar = function () { 
219     this.$body.css('padding-right', '') 
220   } 
221 
 
222   Modal.prototype.measureScrollbar = function () { // thx walsh 
223     if (document.body.clientWidth >= window.innerWidth) return 0 
224     var scrollDiv = document.createElement('div') 
225     scrollDiv.className = 'modal-scrollbar-measure' 
226     this.$body.append(scrollDiv) 
227     var scrollbarWidth = scrollDiv.offsetWidth - scrollDiv.clientWidth 
228     this.$body[0].removeChild(scrollDiv) 
229     return scrollbarWidth 
230   } 
231 
 
232 
 
233   // MODAL PLUGIN DEFINITION 
234   // ======================= 
235 
 
236   function Plugin(option, _relatedTarget) { 
237     return this.each(function () { 
238       var $this   = $(this) 
239       var data    = $this.data('bs.modal') 
240       var options = $.extend({}, Modal.DEFAULTS, $this.data(), typeof option == 'object' && option) 
241 
 
242       if (!data) $this.data('bs.modal', (data = new Modal(this, options))) 
243       if (typeof option == 'string') data[option](_relatedTarget) 
244       else if (options.show) data.show(_relatedTarget) 
245     }) 
246   } 
247 
 
248   var old = $.fn.modal 
249 
 
250   $.fn.modal             = Plugin 
251   $.fn.modal.Constructor = Modal 
252 
 
253 
 
254   // MODAL NO CONFLICT 
255   // ================= 
256 
 
257   $.fn.modal.noConflict = function () { 
258     $.fn.modal = old 
259     return this 
260   } 
261 
 
262 
 
263   // MODAL DATA-API 
264   // ============== 
265 
 
266   $(document).on('click.bs.modal.data-api', '[data-toggle="modal"]', function (e) { 
267     var $this   = $(this) 
268     var href    = $this.attr('href') 
269     var $target = $($this.attr('data-target') || (href && href.replace(/.*(?=#[^\s]+$)/, ''))) // strip for ie7 
270     var option  = $target.data('bs.modal') ? 'toggle' : $.extend({ remote: !/#/.test(href) && href }, $target.data(), $this.data()) 
271 
 
272     if ($this.is('a')) e.preventDefault() 
273 
 
274     $target.one('show.bs.modal', function (showEvent) { 
275       if (showEvent.isDefaultPrevented()) return // only register focus restorer if modal will actually get shown 
276       $target.one('hidden.bs.modal', function () { 
277         $this.is(':visible') && $this.trigger('focus') 
278       }) 
279     }) 
280     Plugin.call($target, option, this) 
281   }) 
282 
 
283 }(jQuery); 
