// Sample Product Data
const products = [
    {
        id: 1,
        name: "Amul Taaza Toned Fresh Milk",
        weight: "500 ml",
        price: 28,
        originalPrice: 30,
        category: "dairy",
        time: "9 MINS",
        badge: "BESTSELLER",
        image: "https://images.unsplash.com/photo-1550583724-b2692b85b150?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 2,
        name: "Brown Bread by English Oven",
        weight: "400 g",
        price: 45,
        originalPrice: 50,
        category: "dairy",
        time: "9 MINS",
        badge: "",
        image: "https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 3,
        name: "Fresh Farm White Eggs - 6 pcs",
        weight: "6 pack",
        price: 49,
        originalPrice: 55,
        category: "dairy",
        time: "9 MINS",
        badge: "SAVE ₹6",
        image: "https://images.unsplash.com/photo-1582722872445-44dc5f7e3c8f?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 4,
        name: "Fresh Desi Cow Butter",
        weight: "100 g",
        price: 56,
        originalPrice: 60,
        category: "dairy",
        time: "9 MINS",
        badge: "",
        image: "https://images.unsplash.com/photo-1589985270826-4b7bb135bc9d?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 5,
        name: "Fresh Alphonso Mangoes",
        weight: "1 kg",
        price: 299,
        originalPrice: 399,
        category: "vegetables",
        time: "11 MINS",
        badge: "SEASONAL",
        image: "https://images.unsplash.com/photo-1553279768-865429fa0078?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 6,
        name: "Fresh Local Tomatoes",
        weight: "500 g",
        price: 24,
        originalPrice: 30,
        category: "vegetables",
        time: "9 MINS",
        badge: "",
        image: "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 7,
        name: "Fresh Cavendish Bananas",
        weight: "6 pcs",
        price: 35,
        originalPrice: 45,
        category: "vegetables",
        time: "9 MINS",
        badge: "22% OFF",
        image: "https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 8,
        name: "Lay's India's Magic Masala Chips",
        weight: "50 g",
        price: 20,
        originalPrice: 20,
        category: "snacks",
        time: "9 MINS",
        badge: "",
        image: "https://images.unsplash.com/photo-1566478989037-eec170784d0b?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 9,
        name: "Kurkure Masala Munch",
        weight: "90 g",
        price: 20,
        originalPrice: 20,
        category: "snacks",
        time: "9 MINS",
        badge: "",
        image: "https://images.unsplash.com/photo-1621939514649-280e2ee25f60?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 10,
        name: "Haldiram's Nagpur Bhujia",
        weight: "400 g",
        price: 135,
        originalPrice: 150,
        category: "snacks",
        time: "9 MINS",
        badge: "POPULAR",
        image: "https://images.unsplash.com/photo-1599488615731-7e5c2823ff26?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 11,
        name: "Coca-Cola Cold Drink Can",
        weight: "300 ml",
        price: 40,
        originalPrice: 45,
        category: "beverages",
        time: "9 MINS",
        badge: "CHILLED",
        image: "https://images.unsplash.com/photo-1554866585-cd94860890b7?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 12,
        name: "Sprite Lemon-Lime Soda",
        weight: "750 ml",
        price: 45,
        originalPrice: 50,
        category: "beverages",
        time: "9 MINS",
        badge: "",
        image: "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 13,
        name: "Maggi 2-Minute Masala Noodles",
        weight: "70 g",
        price: 14,
        originalPrice: 15,
        category: "instant",
        time: "9 MINS",
        badge: "MUST HAVE",
        image: "https://images.unsplash.com/photo-1612927601601-6638404737ce?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 14,
        name: "Top Ramen Curry Noodles",
        weight: "280 g pack",
        price: 85,
        originalPrice: 99,
        category: "instant",
        time: "9 MINS",
        badge: "",
        image: "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&q=80&w=300"
    },
    {
        id: 15,
        name: "Crocin Pain Relief Tablet",
        weight: "15 tablets",
        price: 32,
        originalPrice: 35,
        category: "medicines",
        time: "15 MINS",
        badge: "ESSENTIAL",
        image: "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?auto=format&fit=crop&q=80&w=300"
    }
];

// Categories list for top banner grid
const categories = [
    { name: "Dairy, Bread & Eggs", icon: "https://images.unsplash.com/photo-1550583724-b2692b85b150?auto=format&fit=crop&q=80&w=200", cat: "dairy" },
    { name: "Fruits & Vegetables", icon: "https://images.unsplash.com/photo-1610832958506-aa56368176cf?auto=format&fit=crop&q=80&w=200", cat: "vegetables" },
    { name: "Cold Drinks & Juices", icon: "https://images.unsplash.com/photo-1554866585-cd94860890b7?auto=format&fit=crop&q=80&w=200", cat: "beverages" },
    { name: "Munchies & Snacks", icon: "https://images.unsplash.com/photo-1566478989037-eec170784d0b?auto=format&fit=crop&q=80&w=200", cat: "snacks" },
    { name: "Instant & Frozen Food", icon: "https://images.unsplash.com/photo-1612927601601-6638404737ce?auto=format&fit=crop&q=80&w=200", cat: "instant" },
    { name: "Healthcare & Medicines", icon: "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?auto=format&fit=crop&q=80&w=200", cat: "medicines" }
];

// Cart State: { productId: quantity }
let cart = {};

// DOM Elements
const productGrid = document.getElementById('productGrid');
const categoryGrid = document.getElementById('categoryGrid');
const cartBtn = document.getElementById('cartBtn');
const cartCount = document.getElementById('cartCount');
const cartDrawer = document.getElementById('cartDrawer');
const cartOverlay = document.getElementById('cartOverlay');
const closeCart = document.getElementById('closeCart');
const cartItemsList = document.getElementById('cartItemsList');
const emptyCartState = document.getElementById('emptyCartState');
const cartFooter = document.getElementById('cartFooter');
const billDetails = document.getElementById('billDetails');
const cartDrawerCount = document.getElementById('cartDrawerCount');
const billItemsTotal = document.getElementById('billItemsTotal');
const billGrandTotal = document.getElementById('billGrandTotal');
const footerTotalItems = document.getElementById('footerTotalItems');
const footerTotalPrice = document.getElementById('footerTotalPrice');
const checkoutBtn = document.getElementById('checkoutBtn');
const searchInput = document.getElementById('searchInput');
const clearSearch = document.getElementById('clearSearch');
const activeCategoryTitle = document.getElementById('activeCategoryTitle');
const loginBtn = document.getElementById('loginBtn');
const loginModal = document.getElementById('loginModal');
const closeModal = document.getElementById('closeModal');
const continueBtn = document.getElementById('continueBtn');
const phoneNumber = document.getElementById('phoneNumber');
const toast = document.getElementById('toast');

// Initialize App
document.addEventListener('DOMContentLoaded', () => {
    renderCategories();
    renderProducts(products);
    setupEventListeners();
});

// Render Categories Grid
function renderCategories() {
    categoryGrid.innerHTML = categories.map(cat => `
        <div class="category-card" onclick="filterCategory('${cat.cat}')">
            <div class="category-img-box">
                <img src="${cat.icon}" alt="${cat.name}">
            </div>
            <h4>${cat.name}</h4>
        </div>
    `).join('');
}

// Render Products Grid
function renderProducts(itemsToRender) {
    if (itemsToRender.length === 0) {
        productGrid.innerHTML = `
            <div style="grid-column: 1 / -1; text-align: center; padding: 40px;">
                <i class="fa-solid fa-magnifying-glass" style="font-size: 36px; color: #ccc; margin-bottom: 12px;"></i>
                <h3 style="font-size: 16px; color: #666;">No products found</h3>
                <p style="font-size: 13px; color: #999;">Try searching for something else like 'milk', 'bread', 'maggi'</p>
            </div>
        `;
        return;
    }

    productGrid.innerHTML = itemsToRender.map(product => {
        const qty = cart[product.id] || 0;
        return `
            <div class="product-card">
                ${product.badge ? `<span class="product-badge">${product.badge}</span>` : ''}
                <div class="product-img">
                    <img src="${product.image}" alt="${product.name}">
                </div>
                <div class="product-time">
                    <i class="fa-solid fa-bolt" style="color: #eab308;"></i> ${product.time}
                </div>
                <h4 class="product-name" title="${product.name}">${product.name}</h4>
                <div class="product-weight">${product.weight}</div>
                <div class="product-footer">
                    <div class="price-box">
                        <span class="current-price">₹${product.price}</span>
                        <span class="original-price">₹${product.originalPrice}</span>
                    </div>
                    <div id="action-${product.id}">
                        ${qty === 0 ? `
                            <button class="btn-add" onclick="updateQty(${product.id}, 1)">ADD</button>
                        ` : `
                            <div class="qty-controls">
                                <button onclick="updateQty(${product.id}, -1)"><i class="fa-solid fa-minus"></i></button>
                                <span>${qty}</span>
                                <button onclick="updateQty(${product.id}, 1)"><i class="fa-solid fa-plus"></i></button>
                            </div>
                        `}
                    </div>
                </div>
            </div>
        `;
    }).join('');
}

// Filter Products by Category
function filterCategory(catKey) {
    // Update active filter chip styles
    document.querySelectorAll('.cat-chip').forEach(chip => {
        chip.classList.remove('active');
        if (catKey === 'all' && chip.textContent.includes('All')) chip.classList.add('active');
        if (catKey === 'dairy' && chip.textContent.includes('Dairy')) chip.classList.add('active');
        if (catKey === 'vegetables' && chip.textContent.includes('Fruits')) chip.classList.add('active');
        if (catKey === 'snacks' && chip.textContent.includes('Munchies')) chip.classList.add('active');
        if (catKey === 'beverages' && chip.textContent.includes('Cold')) chip.classList.add('active');
        if (catKey === 'instant' && chip.textContent.includes('Instant')) chip.classList.add('active');
    });

    let filtered = products;
    if (catKey !== 'all') {
        filtered = products.filter(p => p.category === catKey);
    }

    // Set header title
    const titles = {
        'all': 'All Products',
        'dairy': 'Dairy, Bread & Eggs',
        'vegetables': 'Fruits & Vegetables',
        'snacks': 'Munchies & Snacks',
        'beverages': 'Cold Drinks & Juices',
        'instant': 'Instant & Frozen Food',
        'medicines': 'Healthcare & Medicines'
    };
    activeCategoryTitle.textContent = titles[catKey] || 'Products';
    searchInput.value = '';
    clearSearch.style.display = 'none';

    renderProducts(filtered);
    window.scrollTo({ top: 350, behavior: 'smooth' });
}

// Update Cart Quantity
function updateQty(productId, change) {
    if (!cart[productId]) {
        cart[productId] = 0;
    }
    cart[productId] += change;

    if (cart[productId] <= 0) {
        delete cart[productId];
    }

    renderProducts(getCurrentFilteredProducts());
    updateCartUI();
}

// Get current visible products (supporting active search or filter)
function getCurrentFilteredProducts() {
    const query = searchInput.value.toLowerCase().trim();
    if (query) {
        return products.filter(p => p.name.toLowerCase().includes(query) || p.category.toLowerCase().includes(query));
    }
    return products;
}

// Update Cart UI & Sidebar Drawer
function updateCartUI() {
    const totalItems = Object.values(cart).reduce((a, b) => a + b, 0);
    cartCount.textContent = totalItems;
    cartDrawerCount.textContent = totalItems;

    if (totalItems === 0) {
        emptyCartState.style.display = 'block';
        cartItemsList.innerHTML = '';
        cartItemsList.appendChild(emptyCartState);
        cartFooter.style.display = 'none';
        billDetails.style.display = 'none';
        return;
    }

    emptyCartState.style.display = 'none';
    cartFooter.style.display = 'flex';
    billDetails.style.display = 'block';

    let itemsHtml = '';
    let itemsTotal = 0;

    for (const [id, qty] of Object.entries(cart)) {
        const product = products.find(p => p.id == id);
        if (product) {
            const cost = product.price * qty;
            itemsTotal += cost;
            itemsHtml += `
                <div class="cart-item">
                    <img src="${product.image}" alt="${product.name}">
                    <div class="cart-item-info">
                        <h4 class="cart-item-name">${product.name}</h4>
                        <div class="cart-item-weight">${product.weight}</div>
                        <div class="cart-item-price">₹${cost}</div>
                    </div>
                    <div class="qty-controls">
                        <button onclick="updateQty(${product.id}, -1)"><i class="fa-solid fa-minus"></i></button>
                        <span>${qty}</span>
                        <button onclick="updateQty(${product.id}, 1)"><i class="fa-solid fa-plus"></i></button>
                    </div>
                </div>
            `;
        }
    }

    cartItemsList.innerHTML = itemsHtml;
    
    const handlingCharge = 4;
    const grandTotal = itemsTotal + handlingCharge;

    billItemsTotal.textContent = `₹${itemsTotal}`;
    billGrandTotal.textContent = `₹${grandTotal}`;
    footerTotalItems.textContent = `${totalItems} items`;
    footerTotalPrice.textContent = `₹${grandTotal}`;
}

// Setup Event Listeners
function setupEventListeners() {
    // Open Cart Drawer
    cartBtn.addEventListener('click', () => {
        cartDrawer.classList.add('active');
        cartOverlay.classList.add('active');
    });

    // Close Cart Drawer
    closeCart.addEventListener('click', closeCartDrawer);
    cartOverlay.addEventListener('click', closeCartDrawer);

    function closeCartDrawer() {
        cartDrawer.classList.remove('active');
        cartOverlay.classList.remove('active');
    }

    // Search input handler
    searchInput.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase().trim();
        if (query.length > 0) {
            clearSearch.style.display = 'block';
        } else {
            clearSearch.style.display = 'none';
        }

        const filtered = products.filter(p => 
            p.name.toLowerCase().includes(query) || 
            p.category.toLowerCase().includes(query)
        );
        activeCategoryTitle.textContent = query ? `Search results for "${query}"` : 'All Products';
        renderProducts(filtered);
    });

    clearSearch.addEventListener('click', () => {
        searchInput.value = '';
        clearSearch.style.display = 'none';
        activeCategoryTitle.textContent = 'All Products';
        renderProducts(products);
    });

    // Login Modal
    loginBtn.addEventListener('click', () => {
        loginModal.classList.add('active');
    });

    closeModal.addEventListener('click', () => {
        loginModal.classList.remove('active');
    });

    loginModal.addEventListener('click', (e) => {
        if (e.target === loginModal) {
            loginModal.classList.remove('active');
        }
    });

    continueBtn.addEventListener('click', () => {
        const val = phoneNumber.value.trim();
        if (val.length === 10) {
            loginModal.classList.remove('active');
            loginBtn.textContent = `+91 ${val.slice(0, 5)}...`;
            showToast("Login Successful", "Welcome back to Blinkit!");
        } else {
            alert("Please enter a valid 10-digit mobile number");
        }
    });

    // Checkout
    checkoutBtn.addEventListener('click', () => {
        closeCartDrawer();
        cart = {};
        updateCartUI();
        renderProducts(products);
        showToast("Order Placed Successfully! 🚀", "Your groceries will reach you in 9 minutes.");
    });
}

// Show Toast notification
function showToast(title, msg) {
    document.getElementById('toastTitle').textContent = title;
    document.getElementById('toastMsg').textContent = msg;
    toast.classList.add('active');
    setTimeout(() => {
        toast.classList.remove('active');
    }, 4000);
}
