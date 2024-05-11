let popup = document.getElementById("authorize");
function openAuthorization(){
    
    popup.classList.add("open_login");
    
    document.body.style.overflowY = 'hidden';
    popup.classList.remove("close_login");
}
function closeAuthorization(){
    
    popup.classList.add("close_login");
    
    document.body.style.overflowY = 'visible';
    popup.classList.remove("open_login");
}
 
 

//  setTimeout(function(){
//      document.body.style.overflow =  '';
//      window.scrollTo(0,scrollPosition);
//  }, 5000)


// function showSelectedFiles(event) {
//     const fileList = document.getElementById('fileList');
//     const maxFiles = 15; // Максимальное количество файлов
//
//     const files = event.target.files;
//     for (let i = 0; i < files.length; i++) {
//         if (fileList.childElementCount >= maxFiles) {
//             alert(`Вы не можете добавить больше ${maxFiles} файлов`);
//             break;
//         }
//         const file = files[i];
//         const reader = new FileReader();
//
//         reader.onload = function(e) {
//             const thumbnail = document.createElement('div');
//             thumbnail.classList.add('text-center');
//             let element;
//             if (file.type.includes('image')) {
//                 element = document.createElement('img');
//                 element.src = e.target.result;
//             } else if (file.type.includes('video')) {
//                 element = document.createElement('video');
//                 element.src = e.target.result;
//                 element.controls = true;
//             }
//
//             // Создаем кнопку для удаления файла
//             const deleteButton = document.createElement('button');
//             deleteButton.innerHTML = '&times;';
//             deleteButton.classList.add('absolute', 'top-0', 'right-0',
//             'text-red-500', 'p-1');
//             deleteButton.addEventListener('click', function() {
//                 thumbnail.remove();
//             });
//
//             // Добавляем элемент и кнопку в список
//             thumbnail.appendChild(element);
//             thumbnail.appendChild(deleteButton);
//             fileList.appendChild(thumbnail);
//         };
//
//         reader.readAsDataURL(file);
//     }
// }