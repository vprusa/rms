import Api from './api.js'

const api = Api();

export default {
  isDev() {
    return process.env.NODE_ENV && process.env.NODE_ENV === 'development'
  },
  getHouseholds() {
    if (this.isDev()) {
      return new Promise(resolve => resolve([{
        "id": 1,
        "street": "Elm Street",
        "buildingNumber": "666",
        "zipCode": "333 x2",
        "state": "CZ",
        "tenants": [{
          "id": 1,
          "email": "Adam@rms.com",
          "firstName": "Adam",
          "lastName": "Mada"
        }],
        "lists": [{
          "id": 1,
          "name": "AdamsSL",
          "shoppingItems": [{
            "id": 1, "name": "orange", "quantity": 10, "bought": false, "buyer": null, "list": {
              "id": 1,
              "name": "AdamsSL",
              "household": {
                "id": 1,
                "street": "Elm Street",
                "buildingNumber": "666",
                "zipCode": "333 x2",
                "state": "CZ"
              }
            }
          }],
          "household": {"id": 1, "street": "Elm Street", "buildingNumber": "666", "zipCode": "333 x2", "state": "CZ"}
        }]
      }]));
    } else {
      return api.get('/households')
        .catch(err => {
          console.log(err)
        });
    }
  },
  getUsers() {
    if (this.isDev()) {
      return new Promise(resolve => resolve([{
        "id": 1,
        "email": "Adam@rms.com",
        "firstName": "Adam",
        "lastName": "Mada"
      }, {
        "id": 2,
        "email": "Tom@rms.com",
        "firstName": "Tom",
        "lastName": "Mot"
      }, {
        "id": 3,
        "email": "Jan@rms.com",
        "firstName": "Jan",
        "lastName": "Naj"
      }, {
        "id": 4,
        "email": "Lek@rms.com",
        "firstName": "Lek",
        "lastName": "Kel"
      }]));
    } else {
      return api.get('/users')
        .catch(err => {
          console.log(err)
        });
    }
  },
  getShoppingLists() {
    if (this.isDev()) {
      return new Promise(resolve => resolve([{
        "id": 1,
        "name": "AdamsSL",
        "shoppingItems": [{"id": 1, "name": "orange", "quantity": 10, "bought": false, "buyer": null, "list": null}],
        "household": {"id": 1, "street": "Elm Street", "buildingNumber": "666", "zipCode": "333 x2", "state": "CZ"}
      }]));
    } else {
      return api.get('/shoppinglists')
        .catch(err => {
          console.log(err)
        });
    }
  },
  getShoppingItems() {
    if (this.isDev()) {
      return new Promise(resolve => resolve([{
        "id": 1,
        "name": "orange",
        "quantity": 10,
        "bought": false,
        "buyer": null,
        "list": null
      }]));
    } else {
      return api.get('/shoppingitems')
        .catch(err => {
          console.log(err)
        });
    }
  },
  getHousehold(id) {
    if (this.isDev()) {
      return this.getHouseholds().then(h => h.find(h => h.id && h.id === Number(id)));
    }
    return this.getHouseholds()
      .then(hs => hs.find(h => h.id != null && h.id === id));
  },
  getUserHouseholds(uid) {
    if (this.isDev()) {
      return this.getHouseholds();
    }
    return this.getHouseholds()
      .then(hs => hs
        .filter(h => h.tenants != null && h.tenants
          .find(t => t.id === id)));
  },
  getUser(email) {
    return this.getUsers().then(us => us.find(u => u.email != null && u.email === email));
  },
  getShoppingList(id) {
    if (this.isDev()) {
      return {
        "id": 1,
        "name": "AdamsSL",
        "shoppingItems": [{
          "id": 1, "name": "orange", "quantity": 10, "bought": false, "buyer": {
            "id": 1,
            "email": "Adam@rms.com",
            "firstName": "Adam",
            "lastName": "Mada"
          }, "list": {
            "id": 1,
            "name": "AdamsSL",
            "household": {
              "id": 1,
              "street": "Elm Street",
              "buildingNumber": "666",
              "zipCode": "333 x2",
              "state": "CZ"
            }
          }
        }, {
          "id": 2, "name": "Grepfrujt", "quantity": 2, "bought": false, "buyer": {
            "id": 1,
            "email": "Adam@rms.com",
            "firstName": "Adam",
            "lastName": "Mada"
          }, "list": {
            "id": 1,
            "name": "AdamsSL",
            "household": {
              "id": 1,
              "street": "Elm Street",
              "buildingNumber": "666",
              "zipCode": "333 x2",
              "state": "CZ"
            }
          }
        }],
      };
    }
    return this.getShoppingLists().then(sl => sl.find(l => l.id != null && l.id === id));
  },

  getShoppingItem(id) {
    return this.getShoppingItems().then(si => si.find(i => i.id != null && i.id === id));
  },

  buyShoppingItem(id) {
    api.post(`/shoppingitems/buy/${Number(id)}`);
  }
}
