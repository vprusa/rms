import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import User from '@/components/User'
import Household from '@/components/Household'
import ShoppingList from '@/components/ShoppingList'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    }, {
      path: '/user/:id',
      name: 'User',
      component: User,
    }, {
      path: '/household/:id',
      name: 'Household',
      component: Household,
    }, {
      path: '/list/:id',
      name: 'ShoppingList',
      component: ShoppingList,
    }
  ]
})
