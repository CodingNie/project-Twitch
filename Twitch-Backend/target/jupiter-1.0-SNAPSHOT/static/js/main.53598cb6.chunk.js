(this["webpackJsonptwitch-winter1"]=this["webpackJsonptwitch-winter1"]||[]).push([[0],{174:function(e,t,n){},311:function(e,t,n){"use strict";n.r(t);var r=n(5),a=n(0),c=n.n(a),s=n(26),i=n.n(s),o=(n(174),n(50)),l=n(53),u=n(52),d=n(313),h=n(168),j=n(81),f=n(48),m=n(66),b=n(318),p=n(316),O=n(315),g=n(317),x=n(321),y=n(322),v="",S="".concat(v,"/login"),C=function(e){return fetch(S,{method:"POST",headers:{"Content-Type":"application/json"},credentials:"include",body:JSON.stringify(e)}).then((function(e){if(200!==e.status)throw Error("Fail to log in");return e.json()}))},w="".concat(v,"/register"),I=function(e){return fetch(w,{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(e)}).then((function(e){if(200!==e.status)throw Error("Fail to register")}))},F="".concat(v,"/logout"),P=function(){return fetch(F,{method:"POST",credentials:"include"}).then((function(e){if(200!==e.status)throw Error("Fail to log out")}))},k="".concat(v,"/game"),E=function(){return fetch(k).then((function(e){if(200!==e.status)throw Error("Fail to get top games");return e.json()}))},M="".concat(v,"/game?game_name="),T="".concat(v,"/search?game_id="),_=function(e){return fetch("".concat(T).concat(e)).then((function(e){if(200!==e.status)throw Error("Fail to find the game");return e.json()}))},R=function(e){return function(e){return fetch("".concat(M).concat(e)).then((function(e){if(200!==e.status)throw Error("Fail to find the game");return e.json()}))}(e).then((function(e){if(e&&e.id)return _(e.id);throw Error("Fail to find the game")}))},D="".concat(v,"/favorite"),L=function(){return fetch(D,{credentials:"include"}).then((function(e){if(200!==e.status)throw Error("Fail to get favorite item");return e.json()}))},A="".concat(v,"/recommendation"),G=function(){return fetch(A,{credentials:"include"}).then((function(e){if(200!==e.status)throw Error("Fail to get recommended item");return e.json()}))},V=function(e){Object(l.a)(n,e);var t=Object(u.a)(n);function n(){var e;Object(o.a)(this,n);for(var a=arguments.length,c=new Array(a),s=0;s<a;s++)c[s]=arguments[s];return(e=t.call.apply(t,[this].concat(c))).state={displayModal:!1},e.handleCancel=function(){e.setState({displayModal:!1})},e.signinOnClick=function(){e.setState({displayModal:!0})},e.onFinish=function(t){C(t).then((function(t){e.setState({displayModal:!1}),h.b.success("Welcome back, ".concat(t.name)),e.props.onSuccess()})).catch((function(e){h.b.error(e.message)}))},e.render=function(){return Object(r.jsxs)(r.Fragment,{children:[Object(r.jsx)(m.a,{shape:"round",onClick:e.signinOnClick,style:{marginRight:"20px"},children:"Login"}),Object(r.jsx)(p.a,{title:"Log in",visible:e.state.displayModal,onCancel:e.handleCancel,footer:null,destroyOnClose:!0,children:Object(r.jsxs)(O.a,{name:"normal_login",onFinish:e.onFinish,preserve:!1,children:[Object(r.jsx)(O.a.Item,{name:"user_id",rules:[{required:!0,message:"Please input your Username!"}],children:Object(r.jsx)(g.a,{prefix:Object(r.jsx)(x.a,{}),placeholder:"Username"})}),Object(r.jsx)(O.a.Item,{name:"password",rules:[{required:!0,message:"Please input your Password!"}],children:Object(r.jsx)(g.a.Password,{prefix:Object(r.jsx)(y.a,{}),placeholder:"Password"})}),Object(r.jsx)(O.a.Item,{children:Object(r.jsx)(m.a,{type:"primary",htmlType:"submit",children:"Login"})})]})})]})},e}return n}(c.a.Component),q=function(e){Object(l.a)(n,e);var t=Object(u.a)(n);function n(){var e;Object(o.a)(this,n);for(var a=arguments.length,c=new Array(a),s=0;s<a;s++)c[s]=arguments[s];return(e=t.call.apply(t,[this].concat(c))).state={displayModal:!1},e.handleCancel=function(){e.setState({displayModal:!1})},e.signupOnClick=function(){e.setState({displayModal:!0})},e.onFinish=function(t){I(t).then((function(){e.setState({displayModal:!1}),h.b.success("Successfully signed up")})).catch((function(e){h.b.error(e.message)}))},e.render=function(){return Object(r.jsxs)(r.Fragment,{children:[Object(r.jsx)(m.a,{shape:"round",type:"primary",onClick:e.signupOnClick,children:"Register"}),Object(r.jsx)(p.a,{title:"Register",visible:e.state.displayModal,onCancel:e.handleCancel,footer:null,destroyOnClose:!0,children:Object(r.jsxs)(O.a,{name:"normal_register",initialValues:{remember:!0},onFinish:e.onFinish,preserve:!1,children:[Object(r.jsx)(O.a.Item,{name:"user_id",rules:[{required:!0,message:"Please input your Username!"}],children:Object(r.jsx)(g.a,{prefix:Object(r.jsx)(x.a,{}),placeholder:"Username"})}),Object(r.jsx)(O.a.Item,{name:"password",rules:[{required:!0,message:"Please input your Password!"}],children:Object(r.jsx)(g.a,{prefix:Object(r.jsx)(y.a,{}),placeholder:"Password"})}),Object(r.jsx)(O.a.Item,{name:"first_name",rules:[{required:!0,message:"Please input your Firstname!"}],children:Object(r.jsx)(g.a,{placeholder:"firstname"})}),Object(r.jsx)(O.a.Item,{name:"last_name",rules:[{required:!0,message:"Please input your Lastname!"}],children:Object(r.jsx)(g.a,{placeholder:"lastname"})}),Object(r.jsx)(O.a.Item,{children:Object(r.jsx)(m.a,{type:"primary",htmlType:"submit",children:"Register"})})]})})]})},e}return n}(c.a.Component),N=n(319),J=n(323),U=n(324),B=n(325),K=n(326),H=b.a.SubMenu,W="streams",z="videos",Q="clips",X=function(e){Object(l.a)(n,e);var t=Object(u.a)(n);function n(){var e;Object(o.a)(this,n);for(var a=arguments.length,c=new Array(a),s=0;s<a;s++)c[s]=arguments[s];return(e=t.call.apply(t,[this].concat(c))).state={displayDrawer:!1},e.onDrawerClose=function(){e.setState({displayDrawer:!1})},e.onFavoriteClick=function(){e.setState({displayDrawer:!0})},e.render=function(){var t=e.props.data,n=t.VIDEO,a=t.STREAM,c=t.CLIP;return Object(r.jsxs)(r.Fragment,{children:[Object(r.jsx)(m.a,{type:"primary",shape:"round",onClick:e.onFavoriteClick,icon:Object(r.jsx)(J.a,{}),children:"My Favorites"}),Object(r.jsx)(N.a,{title:"My Favorites",placement:"right",width:720,visible:e.state.displayDrawer,onClose:e.onDrawerClose,children:Object(r.jsxs)(b.a,{mode:"inline",defaultOpenKeys:[W],style:{height:"100%",borderRight:0},selectable:!1,children:[Object(r.jsx)(H,{icon:Object(r.jsx)(U.a,{}),title:"Streams",children:a.map((function(e){return Object(r.jsx)(b.a.Item,{children:Object(r.jsx)("a",{href:e.url,target:"_blank",rel:"noopener noreferrer",children:"".concat(e.broadcaster_name," - ").concat(e.title)})},e.id)}))},W),Object(r.jsx)(H,{icon:Object(r.jsx)(B.a,{}),title:"Videos",children:n.map((function(e){return Object(r.jsx)(b.a.Item,{children:Object(r.jsx)("a",{href:e.url,target:"_blank",rel:"noopener noreferrer",children:"".concat(e.broadcaster_name," - ").concat(e.title)})},e.id)}))},z),Object(r.jsx)(H,{icon:Object(r.jsx)(K.a,{}),title:"Clips",children:c.map((function(e){return Object(r.jsx)(b.a.Item,{children:Object(r.jsx)("a",{href:e.url,target:"_blank",rel:"noopener noreferrer",children:"".concat(e.broadcaster_name," - ").concat(e.title)})},e.id)}))},Q)]})})]})},e}return n}(c.a.Component),Y=n(329),Z=n(330),$=n(327),ee=function(e){Object(l.a)(n,e);var t=Object(u.a)(n);function n(){var e;Object(o.a)(this,n);for(var a=arguments.length,c=new Array(a),s=0;s<a;s++)c[s]=arguments[s];return(e=t.call.apply(t,[this].concat(c))).state={displayModal:!1},e.handleCancel=function(){e.setState({displayModal:!1})},e.searchOnClick=function(){e.setState({displayModal:!0})},e.onSubmit=function(t){R(t.game_name).then((function(t){e.setState({displayModal:!1}),e.props.onSuccess(t)})).catch((function(e){h.b.error(e.message)}))},e.render=function(){return Object(r.jsxs)(r.Fragment,{children:[Object(r.jsx)(m.a,{shape:"round",onClick:e.searchOnClick,icon:Object(r.jsx)($.a,{}),style:{marginLeft:"20px",marginTop:"20px"},children:"Custom Search "}),Object(r.jsx)(p.a,{title:"Search",visible:e.state.displayModal,onCancel:e.handleCancel,footer:null,children:Object(r.jsxs)(O.a,{name:"custom_search",onFinish:e.onSubmit,children:[Object(r.jsx)(O.a.Item,{name:"game_name",rules:[{required:!0,message:"Please enter a game name"}],children:Object(r.jsx)(g.a,{placeholder:"Game name"})}),Object(r.jsx)(O.a.Item,{children:Object(r.jsx)(m.a,{type:"primary",htmlType:"submit",children:"Search"})})]})})]})},e}return n}(c.a.Component),te=n(162),ne=n.n(te),re=n(129),ae=n(116),ce=n(314),se=n(320),ie=n(328),oe=re.a.TabPane,le="stream",ue="videos",de="clips",he=function(e,t,n,a){var c="".concat(e.broadcaster_name," - ").concat(e.title),s=n.find((function(t){return t.id===e.id}));return Object(r.jsxs)(r.Fragment,{children:[t&&Object(r.jsx)(ae.a,{title:s?"Remove from favorite list":"Add to favorite list",children:Object(r.jsx)(m.a,{shape:"circle",icon:s?Object(r.jsx)(J.a,{}):Object(r.jsx)(ie.a,{}),onClick:function(){var t;s?(t=e,fetch(D,{method:"DELETE",headers:{"Content-Type":"application/json"},credentials:"include",body:JSON.stringify({favorite:t})}).then((function(e){if(200!==e.status)throw Error("Fail to delete favorite item")}))).then((function(){a()})).catch((function(e){h.b.error(e.message)})):function(e){return fetch(D,{method:"POST",headers:{"Content-Type":"application/json"},credentials:"include",body:JSON.stringify({favorite:e})}).then((function(e){if(200!==e.status)throw Error("Fail to add favorite item")}))}(e).then((function(){a()})).catch((function(e){h.b.error(e.message)}))}})}),Object(r.jsx)("div",{style:{overflow:"hidden",textOverflow:"ellipsis",width:450},children:Object(r.jsx)(ae.a,{title:c,children:Object(r.jsx)("span",{children:c})})})]})},je=function(e,t,n,a){return Object(r.jsx)(ce.b,{grid:{xs:1,sm:2,md:4,lg:4,xl:6},dataSource:e,renderItem:function(e){return Object(r.jsx)(ce.b.Item,{style:{marginRight:"20px"},children:Object(r.jsx)(se.a,{title:he(e,t,n,a),children:Object(r.jsx)("a",{href:e.url,target:"_blank",rel:"noopener noreferrer",children:Object(r.jsx)("img",{alt:"Placeholder",src:(c=e.thumbnail_url,c.replace("%{height}","252").replace("%{width}","480").replace("{height}","252").replace("{width}","480"))})})})});var c}})},fe=function(e){var t=e.resources,n=e.loggedIn,a=e.favoriteItems,c=e.favoriteOnChange,s=t.VIDEO,i=t.STREAM,o=t.CLIP,l=a.VIDEO,u=a.STREAM,d=a.CLIP;return Object(r.jsxs)(re.a,{defaultActiveKey:le,children:[Object(r.jsx)(oe,{tab:"Streams",style:{height:"680px",overflow:"auto"},forceRender:!0,children:je(i,n,u,c)},le),Object(r.jsx)(oe,{tab:"Videos",style:{height:"680px",overflow:"auto"},forceRender:!0,children:je(s,n,l,c)},ue),Object(r.jsx)(oe,{tab:"Clips",style:{height:"680px",overflow:"auto"},forceRender:!0,children:je(o,n,d,c)},de)]})},me=d.a.Header,be=d.a.Content,pe=d.a.Sider,Oe=function(e){Object(l.a)(n,e);var t=Object(u.a)(n);function n(){var e;Object(o.a)(this,n);for(var a=arguments.length,c=new Array(a),s=0;s<a;s++)c[s]=arguments[s];return(e=t.call.apply(t,[this].concat(c))).state={loggedIn:!1,topGames:[],resources:{VIDEO:[],STREAM:[],CLIP:[]},favoriteItems:{VIDEO:[],STREAM:[],CLIP:[]}},e.favoriteOnChange=function(){L().then((function(t){e.setState({favoriteItems:t,loggedIn:!0})})).catch((function(e){h.b.error(e.message)}))},e.onGameSelect=function(t){var n=t.key;"Recommendation"!==n?_(n).then((function(t){e.setState({resources:t})})):G().then((function(t){e.setState({resources:t})}))},e.customSearchOnSuccess=function(t){e.setState({resources:t})},e.signinOnSuccess=function(){L().then((function(t){e.setState({favoriteItems:t,loggedIn:!0})})).catch((function(e){h.b.error(e.message)}))},e.signoutOnClick=function(){P().then((function(){e.setState({loggedIn:!1}),h.b.success("Successfull signed out")})).catch((function(e){h.b.error(e.message)}))},e.componentDidMount=function(){E().then((function(t){e.setState({topGames:t})})).catch((function(e){h.b.error(e.message)}))},e.render=function(){return Object(r.jsxs)(d.a,{children:[Object(r.jsx)(me,{children:Object(r.jsxs)(j.a,{justify:"space-between",children:[Object(r.jsx)(f.a,{children:e.state.loggedIn&&Object(r.jsx)(X,{data:e.state.favoriteItems})}),Object(r.jsx)(f.a,{children:e.state.loggedIn?Object(r.jsx)(m.a,{shape:"round",onClick:e.signoutOnClick,children:"Logout"}):Object(r.jsxs)(r.Fragment,{children:[Object(r.jsx)(V,{onSuccess:e.signinOnSuccess}),Object(r.jsx)(q,{})]})})]})}),Object(r.jsxs)(d.a,{children:[Object(r.jsxs)(pe,{width:300,className:"site-layout-background",children:[Object(r.jsx)(ee,{onSuccess:e.customSearchOnSuccess}),Object(r.jsxs)(b.a,{mode:"inline",onSelect:e.onGameSelect,style:{marginTop:"10px"},children:[Object(r.jsx)(b.a.Item,{icon:Object(r.jsx)(Y.a,{}),children:"Recommend for you!"},"Recommendation"),Object(r.jsx)(ne.a,{icon:Object(r.jsx)(Z.a,{}),title:"Popular Games",className:"site-top-game-list",children:e.state.topGames.map((function(e){return Object(r.jsxs)(b.a.Item,{style:{height:"50px"},children:[Object(r.jsx)("img",{alt:"Placeholder",src:e.box_art_url.replace("{height}","40").replace("{width}","40"),style:{borderRadius:"50%",marginRight:"20px"}}),Object(r.jsx)("span",{children:e.name})]},e.id)}))},"Popular Games")]})]}),Object(r.jsx)(d.a,{style:{padding:"24px"},children:Object(r.jsx)(be,{className:"site-layout-background",style:{padding:24,margin:0,height:800,overflow:"auto"},children:Object(r.jsx)(fe,{resources:e.state.resources,loggedIn:e.state.loggedIn,favoriteItems:e.state.favoriteItems,favoriteOnChange:e.favoriteOnChange})})})]})]})},e}return n}(c.a.Component),ge=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,331)).then((function(t){var n=t.getCLS,r=t.getFID,a=t.getFCP,c=t.getLCP,s=t.getTTFB;n(e),r(e),a(e),c(e),s(e)}))};i.a.render(Object(r.jsx)(c.a.StrictMode,{children:Object(r.jsx)(Oe,{})}),document.getElementById("root")),ge()}},[[311,1,2]]]);
//# sourceMappingURL=main.53598cb6.chunk.js.map