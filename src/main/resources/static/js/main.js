var messageApi=Vue.resource("/message{/id}");

Vue.component('messages-list', {
  props:["messages"],
  template: '<div><div v-for="message in messages">{{message.text}}</div></div>',
  created:function(){
    messageApi.get().then(result=>
        result.json().then(data=>
            data.forEach(message=>this.messages.push(message))
        )
    );
  }

});

var app = new Vue({
    el: '#app',
    template:'<messages-list :messages="messages" />',
    data: {
        messages: [

        ]
    }
});