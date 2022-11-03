/**
 *
 */


//地域のプルダウンボタンが変更された際の処理
function CheckSelectedRegion(obj) {

    let region = obj.value;
    let prefecture_selector = document.getElementById('prefecture');

    // エリアが選択された際に、都道府県を選択可能にする
    if (region >= 1) {
        prefecture_selector.removeAttribute("disabled");
    } else {
        console.log("else処理");
        prefecture_selector.setAttribute("disabled", "disabled");
    }

    // 選択エリアの変更に応じて、選択可能な都道府県を表示する
    hidden_list = document.querySelectorAll('[data-region_id]');
    console.log(hidden_list);
    for (let i = 0; i < hidden_list.length; i++) {
        hidden_list[i].setAttribute("hidden", "");
        hidden_list[i].setAttribute("disabled", "");
    }
    display_list = document.querySelectorAll('[data-region_id="' + region + '"]')
    for (let i = 0; i < display_list.length; i++) {
        display_list[i].removeAttribute("hidden");
        display_list[i].removeAttribute("disabled");
    }

    // 選択エリアを変更した際に、対象外の都道府県・エリアの選択をリセットする
    let index = prefecture_selector.selectedIndex;
    let p_region = document.querySelectorAll('[data-region_id]')[index].dataset.region_id;
    console.log(p_region);
    if (p_region != region) {
        prefecture_selector.options[0].selected = true;
        let area_selector = document.getElementById('area');
        area_selector.options[0].selected = true;
        let store_selector = document.getElementById('store_id');
        store_selector.options[0].selected = true;
    }
}

//都道府県のプルダウンボタンが変更された際の処理
function CheckSelectedPrefecture(obj) {
    let prefecture = obj.value;
    console.log(prefecture);
    let area_selector = document.getElementById('area');

    // 都道府県が選択された際に、エリアを選択可能にする
    if (prefecture >= 1) {
        area_selector.removeAttribute("disabled");
    } else {
        console.log("else処理");
        area_selector.setAttribute("disabled", "disabled");
    }

    // 選択都道府県の変更に応じて、選択可能なエリアを表示する
    hidden_list = document.querySelectorAll('[data-prefecture_id]');
    for (let i = 0; i < hidden_list.length; i++) {
        hidden_list[i].setAttribute("hidden", "");
        hidden_list[i].setAttribute("disabled", "");
    }
    display_list = document.querySelectorAll('[data-prefecture_id="' + prefecture + '"]')
    for (let i = 0; i < display_list.length; i++) {
        display_list[i].removeAttribute("hidden");
        display_list[i].removeAttribute("disabled");
    }

    // 選択都道府県を変更した際に、対象外のエリアの選択をリセットする
    let index = area_selector.selectedIndex;
    let a_prefecture = document.querySelectorAll('[data-prefecture_id]')[index].dataset.prefecture_id;
    console.log(a_prefecture);
    if (a_prefecture != prefecture) {
        area_selector.options[0].selected = true;
        let store_selector = document.getElementById('store_id');
        store_selector.options[0].selected = true;
    }
}

// ここから下は登録画面用js
//エリアのプルダウンボタンが変更された際の処理
function CheckSelectedArea(obj) {
    let area = obj.value;
    console.log(area);
    let store_selector = document.getElementById('store_id');
    console.log(store_selector);


    // エリアが選択された際に、店舗を選択可能にする
    if (area >= 1) {
        store_selector.removeAttribute("disabled");
    } else {
        console.log("else処理");
        store_selector.setAttribute("disabled", "disabled");
    }

    // 選択エリアの変更に応じて、選択可能な店舗を表示する
    hidden_list = document.querySelectorAll('[data-area_id]');
    for (let i = 0; i < hidden_list.length; i++) {
        hidden_list[i].setAttribute("hidden", "");
        hidden_list[i].setAttribute("disabled", "");
    }
    display_list = document.querySelectorAll('[data-area_id="' + area + '"]')
    for (let i = 0; i < display_list.length; i++) {
        display_list[i].removeAttribute("hidden");
        display_list[i].removeAttribute("disabled");
    }

    // 選択エリアを変更した際に、対象外の店舗選択をリセットする
    let index = store_selector.selectedIndex;
    let s_area = document.querySelectorAll('[data-area_id]')[index].dataset.area_id;
    console.log(s_area);
    if (s_area != area) {
        store_selector.options[0].selected = true;
    }
}

//大品目のプルダウンボタンが変更された際の処理
function CheckSelectedMainItem(obj) {
    let main_item = obj.value;
    console.log(main_item);
    let sub_item_selector = document.getElementById('subitem');
    console.log(sub_item_selector);


    // 大品目が選択された際に、中品目を選択可能にする
    if (main_item >= 1) {
        sub_item_selector.removeAttribute("disabled");
    } else {
        console.log("else処理");
        sub_item_selector.setAttribute("disabled", "disabled");
    }

    // 大品目の変更に応じて、選択可能な中品目を表示する
    hidden_list = document.querySelectorAll('[data-main_item_id]');
    for (let i = 0; i < hidden_list.length; i++) {
        hidden_list[i].setAttribute("hidden", "");
        hidden_list[i].setAttribute("disabled", "");
    }
    display_list = document.querySelectorAll('[data-main_item_id="' + main_item + '"]')
    for (let i = 0; i < display_list.length; i++) {
        display_list[i].removeAttribute("hidden");
        display_list[i].removeAttribute("disabled");
    }

    // 大品目を変更した際に、対象外の品目選択をリセットする
    let index = sub_item_selector.selectedIndex;
    let s_main_item = document.querySelectorAll('[data-main_item_id]')[index].dataset.main_item_id;
    console.log(s_main_item);
    if (s_main_item != main_item) {
        sub_item_selector.options[0].selected = true;
        let item_selector = document.getElementById('item');
        item_selector.options[0].selected = true;
    }
}

//中品目のプルダウンボタンが変更された際の処理
function CheckSelectedSubItem(obj) {
    let sub_item = obj.value;
    console.log(sub_item);
    let item_selector = document.getElementById('item');
    console.log(item_selector);


    // 中品目が選択された際に、中品目を選択可能にする
    if (sub_item >= 1) {
        item_selector.removeAttribute("disabled");
    } else {
        console.log("else処理");
        item_selector.setAttribute("disabled", "disabled");
    }

    // 中品目の変更に応じて、選択可能な品目を表示する
    hidden_list = document.querySelectorAll('[data-sub_item_id]');
    for (let i = 0; i < hidden_list.length; i++) {
        hidden_list[i].setAttribute("hidden", "");
        hidden_list[i].setAttribute("disabled", "");
    }
    display_list = document.querySelectorAll('[data-sub_item_id="' + sub_item + '"]')
    for (let i = 0; i < display_list.length; i++) {
        display_list[i].removeAttribute("hidden");
        display_list[i].removeAttribute("disabled");
    }

    // 中品目を変更した際に、対象外の品目選択をリセットする
    let index = item_selector.selectedIndex;
    let i_sub_item = document.querySelectorAll('[data-sub_item_id]')[index].dataset.sub_item_id;
    console.log(i_sub_item);
    if (i_sub_item != sub_item) {
        item_selector.options[0].selected = true;
    }
}