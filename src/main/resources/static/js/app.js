const $ = (s) => document.querySelector(s);
const $$ = (s) => document.querySelectorAll(s);

const modal = $("#accountModal");
const result = $("#apiResult");
const form = $("#accountForm");
const toast = $("#toast");

function showToast(message) {
    toast.textContent = message;
    toast.classList.add("show");
    setTimeout(() => toast.classList.remove("show"), 2600);
}

function showSection(sectionId) {
    $$(".page-section").forEach(s => s.classList.remove("active"));
    $$(".nav-item[data-section]").forEach(n => n.classList.remove("active"));
    const section = document.getElementById(sectionId);
    if (section) section.classList.add("active");
    const nav = document.querySelector(`.nav-item[data-section="${sectionId}"]`);
    if (nav) nav.classList.add("active");
    $("#sidebar").classList.remove("open");
}

$$(".nav-item[data-section]").forEach(item => {
    item.addEventListener("click", () => showSection(item.dataset.section));
});

$$("[data-section-link]").forEach(item => {
    item.addEventListener("click", () => showSection(item.dataset.sectionLink));
});

function openAccountModal() {
    modal.classList.add("open");
    result.className = "api-result";
    result.textContent = "";
    $("#customerId").focus();
}

function closeAccountModal() {
    modal.classList.remove("open");
}

$("#openAccountBtn").addEventListener("click", openAccountModal);
$("#openAccountBtn2").addEventListener("click", openAccountModal);
$("#closeModal").addEventListener("click", closeAccountModal);
modal.addEventListener("click", e => {
    if (e.target === modal) closeAccountModal();
});

$$(".quick-actions button").forEach(btn => {
    btn.addEventListener("click", () => {
        const action = btn.dataset.action;
        if (action === "account") openAccountModal();
        else if (action === "customer") { showSection("customers"); showToast("Customer module selected"); }
        else if (action === "kyc") { showSection("kyc"); showToast("KYC module selected"); }
        else { showSection("transactions"); showToast("Transactions module selected"); }
    });
});

$("#menuBtn").addEventListener("click", () => $("#sidebar").classList.toggle("open"));

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const customerId = $("#customerId").value.trim();
    if (!customerId) return;

    const button = $("#createBtn");
    button.disabled = true;
    button.textContent = "Creating account...";
    result.className = "api-result";
    result.textContent = "";

    try {
        const response = await fetch(
            `/api/v1/account/createAccount?customerId=${encodeURIComponent(customerId)}`,
            { method: "POST" }
        );

        const text = await response.text();

        if (response.ok) {
            result.className = "api-result show success";
            result.textContent = "Account created successfully.";
            showToast("Account created successfully");
        } else if (response.status === 409) {
            result.className = "api-result show error";
            result.textContent = text || "Account already exists for this customer.";
        } else {
            result.className = "api-result show error";
            result.textContent = text || `Request failed with status ${response.status}`;
        }
    } catch (error) {
        result.className = "api-result show error";
        result.textContent = "Could not connect to the Spring Boot server.";
    } finally {
        button.disabled = false;
        button.textContent = "Create Account";
    }
});

$("#globalSearch").addEventListener("keydown", e => {
    if (e.key === "Enter") {
        showToast(`Searching for "${e.target.value}"`);
    }
});
