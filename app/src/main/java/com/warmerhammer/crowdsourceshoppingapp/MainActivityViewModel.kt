package com.warmerhammer.crowdsourceshoppingapp


import androidx.lifecycle.ViewModel
import com.warmerhammer.crowdsourceshoppingapp.data.Account
import com.warmerhammer.crowdsourceshoppingapp.data.Comment
import com.warmerhammer.crowdsourceshoppingapp.data.GroceryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _account = MutableStateFlow(accounts[0])
    val account: StateFlow<Account> = _account

    val _masterShopperPoints = MutableStateFlow(account.value.points)
    val masterShopperPoints: StateFlow<Int> = _masterShopperPoints

    private val _currentpage = MutableStateFlow("homescreen")
    val currentpage: StateFlow<String> = _currentpage
    fun setCurrentPage(destination: String) {
        _currentpage.value = destination
    }

    private val _shoppingCartItems = MutableStateFlow(listOf<GroceryItem>())
    val shoppingCartItems: StateFlow<List<GroceryItem>> = _shoppingCartItems

    // List of Grocery Items - START
    private val _items = MutableStateFlow(groceryItems)
    val items: StateFlow<List<GroceryItem>> = _items
    fun sortByNewest() {
        _items.value = groceryItems.sortedByDescending { it.id }.toMutableList()
    }

    fun sortByLeastVotes() {
        _items.value = groceryItems.sortedBy { it.upvotes }.toMutableList()
    }

    fun sortByMostVotes() {
        _items.value = groceryItems.sortedByDescending { it.upvotes }.toMutableList()
    }
    fun upvoteItem(item: GroceryItem) {
        val index = _items.value.indexOf(item)
        groceryItems[index].upvotes += 1
        _items.value = groceryItems
        // update master shopper points
        _masterShopperPoints.value = _masterShopperPoints.value.plus(1)
        _account.value = _account.value.apply { points += 1 }

    }
    fun downvoteItem(item: GroceryItem) {
        val index = _items.value.indexOf(item)
        _items.value[index].upvotes -= 1
        // update master shopper points
        if (_account.value.points >= 0) {
            _masterShopperPoints.value -= 1
            _account.value = _account.value.apply { points -= 1 }
        }
    }

    fun addShoppingCartItem(item: GroceryItem) {
        _shoppingCartItems.value += item
    }

    fun getGroceryItem(id: Long): GroceryItem {
        return _items.value.filter { it.id == id }[0]
    }

    fun saveGroceryItem(item: GroceryItem) {
        val index = _items.value.indexOf(item)
        _items.value[index] = item
    }

    // List of Grocery Items - END


    private val _comments = MutableStateFlow(savedComments)
    val comments: StateFlow<List<Comment>> = _comments

    fun getComments(itemId: Long) {
        _comments.value = savedComments.filter { it.itemId == itemId }
    }

    fun addBlankComment(comment: Comment) {
        if (!_comments.value.last().isEditable) {
            savedComments += comment
            _comments.value = savedComments
        }
    }

    fun deleteComment(commentId: Long) {
        val index = _comments.value.indexOfFirst { it.itemId == commentId }
        savedComments = savedComments.filterIndexed { idx, _ -> idx != index }
        _comments.value = savedComments
    }

    fun saveComment(commentText: String, commentId: Long) {
        val index = _comments.value.indexOfFirst { it.commentId == commentId }
        savedComments[index].comment = commentText
        savedComments[index].isEditable = false
        _comments.value = savedComments
    }

    private val _tagsPerItem = MutableStateFlow(listOf<String>())
    val tagsPerItem: StateFlow<List<String>> = _tagsPerItem
    fun addTagPerItem(itemId: Long, tag: String) {
        val index = groceryItems.indexOfFirst { it.id == itemId }
        groceryItems[index].tags.add(tag)
        _tagsPerItem.value += tag
    }

    fun getTagsPerItem(itemId: Long) {
        _tagsPerItem.value = groceryItems.filter { it.id == itemId }[0].tags
    }


}


var groceryItems = mutableListOf<GroceryItem>(
    GroceryItem(
        name = "Banana",
        description = "A yellow fruit",
        price = 0.99f,
        image = "android.resource://com.warmerhammer.crowdsourceshoppingapp/drawable/banana",
        category = "Fruit",
        id = 0L,
        tags = mutableListOf("fruit", "yellow", "banana", "long", "ripe", "peel"),
        store = "Safeway"
    ),
    GroceryItem(
        name = "Apple",
        description = "A red fruit",
        price = 2.99f,
        image = "android.resource://com.warmerhammer.crowdsourceshoppingapp/drawable/apple",
        category = "Fruit",
        id = 1L,
        tags = mutableListOf("fruit", "red", "apple"),
        store = "Kroegers"
    ),
    GroceryItem(
        name = "Ice Cream",
        description = "An orange fruit",
        price = 1.99f,
        image = "android.resource://com.warmerhammer.crowdsourceshoppingapp/drawable/ice_cream",
        category = "Dessert",
        id = 2L,
        tags = mutableListOf("dessert", "orange", "ice cream"),
        store = "Farmer's Market"
    ),
    GroceryItem(
        name = "Milk",
        description = "A white drink",
        price = 3.99f,
        image = "android.resource://com.warmerhammer.crowdsourceshoppingapp/drawable/milk",
        category = "Dairy",
        id = 3L,
        tags = mutableListOf("dairy", "white", "milk")
    ),
)

var savedComments = listOf(
    Comment(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        itemId = 0L,
        userId = 0
    ),
    Comment(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ornare massa eget egestas purus viverra accumsan in nisl. Morbi tristique senectus et netus et malesuada fames ac turpis. Tincidunt lobortis feugiat vivamus at augue eget. Rhoncus aenean vel elit scelerisque mauris pellentesque pulvinar. Netus et malesuada fames ac turpis egestas sed. Tristique nulla aliquet enim tortor. Nisl nisi scelerisque eu ultrices vitae. Sed turpis tincidunt id aliquet risus feugiat. In fermentum et sollicitudin ac orci phasellus egestas tellus rutrum. Libero volutpat sed cras ornare arcu dui. In iaculis nunc sed augue lacus viverra vitae congue eu. Aenean sed adipiscing diam donec adipiscing tristique risus nec. Netus et malesuada fames ac turpis egestas. In ante metus dictum at. Ut placerat orci nulla pellentesque dignissim enim. Tincidunt vitae semper quis lectus nulla at volutpat diam ut.",
        itemId = 1L,
        userId = 1
    )
)

val accounts = listOf(
    Account(
        name = "John Doe",
        email = "johndoe@gmail.com",
        password = "password",
        id = 0,
        points = 100,
    ),
    Account(
        name = "Jane Doe",
        email = "janedoe@gmail.com",
        password = "password",
        id = 1,
        points = 100,
    )
)