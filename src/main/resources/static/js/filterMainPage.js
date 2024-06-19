document.addEventListener("DOMContentLoaded", function() {
    const searchButton = document.querySelector(".poppins-bold");
    searchButton.addEventListener("click", filterEstates);
});

function filterEstates() {
    // Получаем выбранные значения фильтров
    const selectedPropertyTypes = getSelectedCheckboxValues("estateDropdownBgHover");
    const selectedRoomNumbers = getSelectedCheckboxValues("dropdownBgHover");
    const minPrice = document.getElementById("min-price").value;
    const maxPrice = document.getElementById("max-price").value;
    const selectedCity = document.querySelector("input[list='citiesList']").value;
    selectedCityDecoded = decodeURIComponent(selectedCity);
    // Фильтрация пустых значений и присвоение переменным filteredSelectedPropertyTypes и filteredRooms
    const filteredSelectedPropertyTypes = selectedPropertyTypes.filter(type => type.trim() !== "");
    const filteredRooms = selectedRoomNumbers.filter(type => type.trim() !== "");

    // Выводим выбранные значения в консоль (пока для проверки)
    // console.log("Selected Property Types:", selectedPropertyTypes);
    // console.log("Selected Room Numbers:", selectedRoomNumbers);
    // console.log("Min Price:", minPrice);
    // console.log("Max Price:", maxPrice);
    console.log("Selected City:", selectedCityDecoded);

    // Формируем объект data для отправки на сервер


    // Отправляем AJAX запрос на сервер
    $.ajax({
        type: "GET",
        url: "/estates/allEstatesByCity?city=" + encodeURIComponent(selectedCityDecoded),

        dataType: 'json',
        success: function(response) {
            // Обработка успешного ответа от сервера
            console.log("Response from server:", response);
            window.location.href = "/html?estates=" + encodeURIComponent(JSON.stringify(response));
            console.log("Response from server:", encodeURIComponent(JSON.stringify(response)));
        },
        error: function(xhr, status, error) {
            // Обработка ошибки
            console.error("Error:", error);
        }
    });

    // Далее можно добавить логику фильтрации объектов и их отображения
}
function sumSelectedRoomNumbers(roomNumbers) {
    return roomNumbers.reduce((total, current) => total + parseInt(current), 0);
}
function getSelectedCheckboxValues(checkboxId) {
    const checkboxes = document.querySelectorAll(`#${checkboxId} input[type=checkbox]:checked`);
    const values = [];
    checkboxes.forEach((checkbox) => {
        values.push(checkbox.nextSibling.textContent.trim());
    });
    return values;
}

