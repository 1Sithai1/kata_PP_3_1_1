let urlPaging = 'http://localhost:8080/api/pag';
let path = '';

const tbodyUsers = document.getElementById('tbodyUsers');
const pageNumbers = document.getElementById('pagingUsers');
const newPaging = document.getElementById('newPaging');


paginationUsers();
pageUsers(0);

function pageUsers(pageNumber) {
    let table = '';

    fetch(urlPaging + '?page=' + pageNumber)
        .then(res => res.json())
        .then(page => {

            //Отрисовка пользователей в актуальном контенте
            page.content.forEach(user => {
                table += `
            <tr>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
            </tr>`;
            })
            tbodyUsers.innerHTML = table;
        })
}

function paginationUsers() {

    let newPag = '';

    fetch(urlPaging)
        .then(res => res.json())
        .then(page => {

            for (let i = 0; i < page.maxPages; i++) {
                path = 'http://localhost:8080/api/pag?'
                path += `page=${i}`
                newPag += `<li class="page-item" id="${i}"><a class="page-link">${i + 1}</a></li>`
            }
            newPaging.innerHTML = newPag;
            let pages = document.getElementById('newPaging').getElementsByTagName('li');
            for (const page of pages) {
                page.addEventListener('click', () => {
                    for (const page1 of pages) {
                        page1.classList.remove('active')
                    }
                    page.classList.add('active')
                    pageUsers(page.id)
                })
            }
        })
}

