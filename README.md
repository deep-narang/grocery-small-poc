Spring boot + H2 DB

BASE URL = /grocery

All the API's

Admin Authority
Username : ionadmin
Password : password

1. Add Product = /add
2. Fetch all products = /all
3. Remove Product = /remove/{id} (id = product id)
4. Update Item = /update/{id} (id = product id)
5. Update Inventory = /manage/id/{id}/inventory/{inventory}
6. All Users = /users

User Authority
Username : ionuser
Password : password

1. View Cart = /cart/view
2. Add to Cart = /cart/add/{id}
3. Update Cart = /cart/update/{productId}
4. Remove from Cart = /cart/remove/{productId}
