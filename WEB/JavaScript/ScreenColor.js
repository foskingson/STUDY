function set_screen_color(self){
    var target= document.querySelector('body');
    if(self.value==='밤'){
        target.style.backgroundColor='black';
        self.value='낮';
    }
    else if(self.value==='낮'){
        target.style.backgroundColor='white';
        self.value='밤';
    }
}