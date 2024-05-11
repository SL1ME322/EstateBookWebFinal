// function changeEstateType(type, button) {
//     var input = document.createElement('estateType')
//     input.value = type;
//     var buttons = document.querySelectorAll('#estate_type button');
//     buttons.forEach(function(btn) {
//         btn.classList.remove('bg-blue-300');
//     });
//
//     // Добавляем стиль только к нажатой кнопке
//     button.classList.add('bg-blue-300');
//     console.log(input.value);
// }
const modelContents = [];
function showSelectedModels(event) {
    const modelList = document.getElementById('modelList');
    const maxModels = 15;
    const files = event.target.files;

    for (let i = 0; i < files.length; i++) {
        if (modelList.childElementCount >= maxModels) {
            alert(`Вы не можете добавить больше ${maxModels} моделей`);
            break;
        }

        const file = files[i];
        const reader = new FileReader();

        reader.onload = function(e) {
            // Сохраняем содержимое файла в другую переменную
            const modelContent = e.target.result;

            // Создаем элемент для отображения названия файла
            const fileNameElement = document.createElement('div');
            fileNameElement.classList.add('text-center');
            fileNameElement.innerText = file.name;

            // Создаем кнопку для удаления
            const deleteButton = document.createElement('button');
            deleteButton.innerHTML = '&times;';
            deleteButton.classList.add('absolute', 'top-0', 'right-0', 'text-red-500', 'p-1');
            deleteButton.addEventListener('click', function() {
                thumbnail.remove();
            });

            // Создаем обертку для элемента с названием и кнопкой удаления
            const thumbnail = document.createElement('div');
            thumbnail.classList.add('text-center');
            thumbnail.appendChild(fileNameElement);
            thumbnail.appendChild(deleteButton);

            // Добавляем созданный элемент на страницу
            modelList.appendChild(thumbnail);

            // Добавляем содержимое файла в массив моделей
            modelContents.push(modelContent);
        };

        // Читаем содержимое файла как текст
        reader.readAsText(file);
    }
}

function showSelectedFiles(event){
    const fileList = document.getElementById('fileList');
    const maxFiles = 15;
    const files = event.target.files;
    for (let i = 0; i < files.length; i++){
        if (fileList.childElementCount >= maxFiles){
            alert(`Вы не можете добавить больше ${maxFiles} файлов`);
            break;
        }
        const file = files[i];
        const reader = new FileReader();
        reader.onload = function(e){
            const thumbnail = document.createElement('div');
            thumbnail.classList.add('text-center');
            let element;
            if (file.type.includes('image')){
                element = document.createElement('img');
                element.src = e.target.result;

            }
            else if (file.type.includes('video')){
                element = document.createElement('video');
                element.src = e.target.result;
                element.controls = true;
            }

            const deleteButton = document.createElement('button');
            deleteButton.innerHTML =  '&times;';
            deleteButton.classList.add('absolute', 'top-0','right-0','text-red-500','p-1');
            deleteButton.addEventListener('click', function () {
                thumbnail.remove();
                }
            )
            thumbnail.appendChild(element);
            thumbnail.appendChild(deleteButton);
            fileList.appendChild(thumbnail);

        };
        reader.readAsDataURL(file)
    }
}

document.getElementById('fileInput').addEventListener('change', showSelectedFiles);




document.getElementById('modelInput').addEventListener('change', showSelectedModels);

var inputs ={
      estate_input: document.getElementById("estate_name_input"),
    floor_input : document.getElementById("floor_input"),
     floor_amount_input :document.getElementById("floor_amount_input"),
     square_input : document.getElementById("square_input"),
     room_amount : document.getElementById("roomAmount"),
    price_input : document.getElementById("price_input"),
    price_for_month_input : document.getElementById("price_for_month_input"),
    description_input : document.getElementById("description_input"),
}
var vars ={
    entrance: null,
    possible_purpose: null,
    purpose: null,
    renovation:null,
    estate_type:null,
    building_type:null,
}


var selectedOptions ={
    "Я -": "",
    "Новая недвижимость": "",
    "Назначение": "",
    "Возможное назначение": "",
    "Тип здания": ""
}

function selectOptions(option, button) {


    const parent = button.parentNode;
    console.log(parent.id);
    var me = "";
    var newEstate = "";
    var aimCommertial = "";
    var possibleAim = "";

    var location = "";
    var name = "";
    var floor = "";
    var floorCount = "";
    var area  = "";
    var entrance  = "";
    var repair  = "";
    var roomCount  = "";
    var repair  = "";
    switch (parent.id) {
        case  "me":
            const buttons = parent.querySelectorAll('button')
            buttons.forEach(btn =>{
                btn.classList.remove('bg-blue-300');
            })
             me = button.textContent

            console.log("me" + me);
            button.classList.add('bg-blue-300');
            break;
        case  "newEstate":
            const buttons2 = parent.querySelectorAll('button')
            buttons2.forEach(btn =>{
                btn.classList.remove('bg-blue-300');
            })
            // var me = button.textContent

            console.log("me" + me);
            button.classList.add('bg-blue-300');
            break;
        case  "aimCommertial":
            const buttons3 = parent.querySelectorAll('button')
            buttons3.forEach(btn =>{
                btn.classList.remove('bg-blue-300');
            })
            vars.purpose = button.textContent

            console.log("me" + me);
            button.classList.add('bg-blue-300');
            break;
        case  "possibleAim":
            const buttons4 = parent.querySelectorAll('button')
            buttons4.forEach(btn =>{
                btn.classList.remove('bg-blue-300');
            })
            vars.possible_purpose = button.textContent

            console.log("me" + me);
            button.classList.add('bg-blue-300');
            break;
        case  "building_type":
            const buttons5 = parent.querySelectorAll('button')
            console.log(buttons5)
            buttons5.forEach(btn =>{
                btn.classList.remove('bg-blue-300');
            })
            vars.building_type = button.textContent;
            console.log(vars.building_type);
            console.log(vars.building_type);console.log(vars.building_type);console.log(vars.building_type);console.log(vars.building_type);console.log(vars.building_type);console.log(vars.building_type);console.log(vars.building_type);console.log(vars.building_type);console.log(vars.building_type);
            button.classList.add('bg-blue-300');
            break;
        case  "entrance":
            const buttons6 = parent.querySelectorAll('button')
            buttons6.forEach(btn =>{
                btn.classList.remove('bg-blue-300');
            })
            vars.entrance = button.textContent

            console.log("me" + me);
            button.classList.add('bg-blue-300');
            break;
        case  "renovation":
            const buttons7 = parent.querySelectorAll('button')
            buttons7.forEach(btn =>{
                btn.classList.remove('bg-blue-300');
            })
            vars.renovation = button.textContent

            console.log("me" + vars.renovation);
            button.classList.add('bg-blue-300');
            break;
        default:
            console.log("ыыыы"  );
    }


}

function addEstate(va){
    var fileList = document.getElementById('fileList');
    var thumbnails = fileList.querySelectorAll('div.text-center');
    var filePaths = [];


    var modelURLs = [];

    var modelInput = document.getElementById('modelInput');
    var modelList = document.getElementById('modelList');
    var modelThumbnails = modelList.querySelectorAll('div.text-center');
    console.log(modelThumbnails);
    modelInput.addEventListener('change', function(event) {
        var files = event.target.files; // Получаем выбранные пользователем файлы
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            if (file.type === 'model/gltf+json' || file.type === 'model/gltf-binary' || file.name.endsWith('.glb') || file.name.endsWith('.gltf')) {
                var reader = new FileReader();
                reader.onload = function(event) {
                    // Добавляем содержимое файла в массив моделей
                    modelURLs.push(event.target.result);
                };
                // Читаем содержимое файла как Data URL
                reader.readAsDataURL(file);
                console.log( file )
            }
        }
    });
    // Прох одимся по каждому элементу списка файлов и извлекаем путь к файлу
    thumbnails.forEach(function(thumbnail) {
        var image = thumbnail.querySelector('img');
        var video = thumbnail.querySelector('video');

        if (image) {
            filePaths.push(image.src);
        } else if (video) {
            filePaths.push(video.src);
        }
    });
    var estateData = {
        adName: inputs.estate_input.value,
        location:  globalAddress ,
        price: inputs.price_input.value, // Цена
        priceForMonth: inputs.price_for_month_input.value, // Цена за месяц
        mortgagePrice: inputs.price_for_month_input.value, // Ипотечная цена
        area: inputs.square_input.value, // Площадь
        //ИСПРАВИТЬ houseArea
        houseArea: inputs.square_input.value, // Площадь дома
        metroStation: "Ближайшая станция метро",
        trainStation: "Ближайшая ж/д станция",
        description: inputs.description_input.value,
        adDate: "2000-12-12", // Дата объявления
        buildingDate: "2022-01-01", // Дата постройки
        status: "Доступно", // Статус недвижимости
        estateRating: 0, // Рейтинг недвижимости
        estate_type: vars.estate_type,
        user: { id: 1 }, // ID пользователя
        renovation: vars.renovation, // Ремонт
        roomAmount: inputs.room_amount.value, // Количество комнат
        purpose: vars.purpose, // Назначение
        possiblePurpose: vars.possible_purpose,
        building_type: "Какой-то тип здания",// Возможное назначение
        address: "Адрес недвижимости",
        floorAmount: inputs.floor_amount_input.value, // Количество этажей
        floor: inputs.floor_input.value, // Этаж
        entrance: vars.entrance,
        city: city,
        files: filePaths,
        threeDModel: modelContents
    };

    console.log(modelContents)

console.log(JSON.stringify(estateData))
    $.ajax({
        type: "POST",
        url: "/estates/createEstate",
        contentType: "application/json",
        data: JSON.stringify(estateData),
        timeout: 90000,
        success: function(result) {
            //location.href = "/html"
        },
        error: function (xhr, status, error) {
            console.error("Ошибка при добавлении недвижимости:", error, status, xhr.responseText);
        }

    });
}
