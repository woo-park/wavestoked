
            
            
            
    const _itemsUndoList = new WeakMap();
    const _itemsRedoList = new WeakMap();

    class TableStack {
        constructor() {
            _itemsUndoList.set(this, []);
            _itemsRedoList.set(this, []);
            this.maxStep = 0;
        }

     
        // redoList
        pushRedoList(step, html) {
            let obj = {[step]: html}
            console.log(obj,'obj!')
            _itemsRedoList.get(this).push(obj);

            this.log();
        }

        popRedoList() {
            const size = _itemsRedoList.get(this).length;
            if(size === 0) 
                throw new Error('Redo Stack is empty');
            
            return _itemsRedoList.get(this).pop();
        }

        peekRedoList() {
            const redoItems = _itemsRedoList.get(this);
            if(redoItems.length === 0) 
                throw new Error ('Stack is empty');
            return redoItems[redoItems.length - 1];
        }

        get countRedoList() {
            return _itemsRedoList.get(this).length;
        }

        emptyRedoList() {

            _itemsRedoList.get(this).splice(0, this.countRedoList);
        }


        // undoList
        pushUndoList(html) {
            let step = this.countUndoList;
            let obj = {[`step${step}`]: html}

            _itemsUndoList.get(this).push(obj);
            
            this.log();
            this.maxStep += 1;

        }

        popUndoList() {
            const L = _itemsUndoList.get(this).length;
            if(L===0)
                throw new Error('Stack is empty');
            
            this.maxStep -= 1;
            return _itemsUndoList.get(this).pop();
        }

        peekUndoList() {
            const items = _itemsUndoList.get(this);
            if(items.length === 0)
                throw new Error ('Stack is empty');
            return items[items.length-1];
        }

        get countUndoList() {
            return _itemsUndoList.get(this).length;
        }

        log() {
            // console.log("_itemsUndoList",_itemsUndoList.get(TableStack));
            console.log("_itemsUndoList",_itemsUndoList.get(this));
            // console.log("_itemsUndoList",_itemsUndoList.get(0));
            // console.log("_itemsRedoList",_itemsRedoList.get(this));
            console.log("_itemsRedoList",_itemsRedoList.get(this));
        }
    }

     //stack.push('a')
    //stack.push(1)
    //stack.count   => 2
    //stack.peek()  => 1
    //stack.pop()   => 1
    //stack.pop()   => "a"
    //stack.count   => 0
    //stack.pop()   => Error Stack is empty
   
    /*
    need to set the max 
    _____
    |___|      push & pop & peek & count & empty 
    |___|       
    |___|                 4 
    |___|               3 3
    |___|             2 2 2
    |___|           1 1 1 1
    
    */




    const stack = new TableStack();

    let column;
    let row;
   
    let columnStart;
    let rowStart;
    let insertingRowCount = 0;
    let insertingColCount = 0;
    // let columnEnd;
    // let rowEnd;

    let dividingRowCount;
    let dividingColCount;
    
    let alertMessage = '';

    bindEventListener();
    syncWithTable();    // 안에서 invoked bindEventListener();
   


    $(".tableColumn").on("click", generateColumns);
    $(".tableColumn").on("keydown", function (event) {
        if (event.keyCode == 13) { // enter
            generateColumns();
        }
    });

    $(".tableRow").on("click", generateRows);
    $(".tableRow").on("keydown", function (event) {
        if (event.keyCode == 13) { // enter
            generateRows();
        }
    });


    function bindEventListener() {
        $(".tbCell").off("click", toggleSelected);

        $(".tbCell").each(function() {
            $(this).on("click", toggleSelected);
        })

        AddEditItemEvent();
        $('[data-action]').off('click', processEditorMenu);                                                                           //re-bind btn eventlisteners
        $('[data-action]').on('click', processEditorMenu);   

    }

    function showAlertMessage() {
        $(".alertMessage").text(alertMessage);
    }

    function toggleSelected(event) {
        $(this).toggleClass("tbCell_selected")
    }
 

    function syncWithTable() {
        generateColumns();
        generateRows();
        
        // // 맨처음 <- stack에 추가 bc change가 일어난후에 돌아올 step이 있어야 하기 때문
        // let currentHtml = $(".tbBody");
        // stack.pushUndoList(currentHtml.html());

        bindEventListener();
    }


    $("#redo").off("click");
    $("#redo").on("click", function(event) {
        console.log('redobtn clicked');

        if(stack.countRedoList > 0) {

            // undo stack에 반영 되기 전 html을 추가한다        // 되돌리기 가능하게 준비한다
            stack.pushUndoList($(".tbBody").html());
            
            let previousTree;
            
            // redo stack에서 most recent 을 가져온다
            previousTree = stack.popRedoList();
            // console.log("previousTree:", Object.values(previousTree)[0])
            
            // 반영
            $(".tbBody").html($(Object.values(previousTree)[0]));
            bindEventListener();
        }
    });

    $("#undo").off("click");
    $("#undo").on("click", function(event) {
        console.log('undobtn clicked');
        
        if(stack.countUndoList > 0) {
            // 전 step 을 찾아온다
            let previousTree;
            previousTree = stack.popUndoList();            
            // console.log("previousTree:", Object.values(previousTree)[0])

            let step = Object.keys(previousTree)[0];

            // let stepRegex = /(step)/g
            step = step.replace("step", "");

            step = Number(step) + 1;
            
            // 현재 html을 redo stack에 추가한다            // 되돌리기를 사용하는 중 -> 즉 redo 할 수 있기 때문에  
            let currentHtml = $(".tbBody");
            stack.pushRedoList(`step${step}`, currentHtml.html());       


           

            // 반영
            $(".tbBody").html($(Object.values(previousTree)[0]));
            bindEventListener();
        }
    })



    // × 다시 병합 된 셀을 병합할 수는 없습니다. 그들을 분할 하십시오.
    // 사이즈가  줄어버린 테이블일경우 <- alert창 필요


    //처음에 한번돌아가야하는데...
    function exceptionHandler(selectedCellCount) {
        
        let breakOut = false;
        $(".tbCell_selected").each((index, each) => {
            if($(each).hasClass("merged")) {
                breakOut = true;
                alertMessage = "merged된 cell을 먼저 나눠주세요."
                return false;
            }
        });
        if(breakOut == true) {
            return false;
        }

        let odd = false;
        let even = false;

        if( selectedCellCount <= 1) {
            alertMessage = "cell을 하나 이상 선택 해주세요."
            return false;
        }
        if( selectedCellCount % 2 == 0) {
            even = true;
        } else {
            odd = true;
        }

        let idealCellCounts = 0;
        
        let xDistance;
        let yDistance;

        
        insertingColCount == 0 ? xDistance = 1 : xDistance = insertingColCount;
        insertingRowCount == 0 ? yDistance = 1 : yDistance = insertingRowCount;

        idealCellCounts = xDistance * yDistance;
        if(idealCellCounts == selectedCellCount) {
            if(insertingColCount > 1 && insertingRowCount > 1) {
                if(odd == true) {

                    if(xDistance == yDistance) {
                        return true;
                    }
                    
                    alertMessage = "테이블이 정사각형 모양으로 골라주세요."
                    return false;
                } else {
                    return true;
                }
           
            } else {
                return true;
            }
        } else {
            alertMessage = "테이블을 사각형 모양으로 골라주세요."
            return false;
        }
        
    }
    

    $("#merge").off("click")
    $("#merge").on("click", function (event) {
        console.log("merge btn clicked! ")

        // early exception
        if($(".tbCell_selected").length < 2) {
            alertMessage = "하나 이상의 cell만 merge가능합니다.";
            showAlertMessage();
            return false;
        }
        
        let currentHtml = $(".tbBody");
        // console.log(currentHtml.html());
        // if redo에서 돌아온후, edit을 한경우면, undolist에 push하면 안됨

        // if(stack.countRedoList > 0) {

        // } else {
            
        // }

        let totalCount = $(".tbCell_selected").length;
        // console.log(totalCount);

        let xCounter = 1;
        let yCounter = 0;
        let tempContainer;
        $(".tbCell_selected").each(function (index) {
            
            let elm = $(this);
           
            let elmContainer = elm.parent();
           
            if(tempContainer && elmContainer.get(0) == tempContainer.get(0)) {
                if(yCounter < 2) {
                    xCounter += 1;
                }
            } else {
                yCounter += 1;
            }
            tempContainer = elmContainer;


            console.log(xCounter, yCounter, ': xLength, yLength');


            let rowIndex = elm.parent().children().index(elm);
            // row 에 
            let colIndex = elmContainer.parent().children().index(elmContainer);



            if (index == 0) {
                columnStart = colIndex;
                rowStart = rowIndex;
            }

            // if (index == (totalCount - 1)) {
            //     columnEnd = colIndex;
            //     rowEnd = rowIndex;
            // }

        });

        // 이렇게 간격을 찾는 것도 방법이지만, 이럴시에는 // merge하려는 cell 전에 merged되있는 cells가있으면 columnEnd와 start포지션을 못잡는다 
         
        // insertingRowCount = Math.abs(columnStart - columnEnd) + 1;
        // insertingColCount = Math.abs(rowStart - rowEnd) + 1;
        insertingRowCount = yCounter;
        insertingColCount = xCounter;

        
        let valid = true;
        valid = exceptionHandler(totalCount);
        if(valid == false) {

            // break early 
            showAlertMessage();
            return false;
        } 

        stack.pushUndoList(currentHtml.html());

        // 하나씩 지우기
        $(".tbCell_selected").each(function (index) {
            let elm = $(this);
            let elmContainer = elm.parent();
            let rowIndex = elm.parent().children().index(elm);
            let colIndex = elmContainer.parent().children().index(elmContainer);

            findAndDelete(colIndex, rowIndex);

        });

        // 첫 cell에 생성한후 (merged cell)넣어준다
        fillInCell(columnStart, rowStart);

        stack.emptyRedoList();

        alertMessage = "";
        showAlertMessage();
      
    
    });

    
    $("#divide").off("click");
    $("#divide").on("click", function (event) {
        console.log("divide btn clicked!");

        // exception
        if(!$(".tbCell_selected:first").hasClass("merged")) {
            alertMessage = "merged된 cell만 나눌수있습니다."
            showAlertMessage();
            return false;
        }


        let currentHtml = $(".tbBody");
        stack.pushUndoList(currentHtml.html());


        let totalCount = $(".tbCell_selected").length;
         

        $(".tbCell_selected").each(function (index) {
            let elm = $(this);
            let elmContainer = elm.parent();

            let rowSpan = elm.attr("rowspan");
            let colSpan = elm.attr("colspan");

            let rowIndex = elm.parent().children().index(elm);
            let colIndex = elmContainer.parent().children().index(elmContainer);

            // if(index == (totalCount - 1)) {
            //     columnEnd = colIndex;
            //     rowEnd = rowIndex;
            // }

            dividingColCount = colSpan;
            dividingRowCount = rowSpan;

            // exception handler
            if(totalCount > 1) {
                alertMessage = "????";
                return false;
            }    


            // merged cell이 뒤에있을경우
            // 앞의 merged cell을 divide 하면 뒤에께 앞당겨온다


            divideCell(rowIndex, colIndex);

            elm.remove();
        });


    });


    function divideCell(col, row) {
        let tbBody = $(".tbBody");

        // let tbRow = tbBody.children().eq(row);

        // console.log(tbRow,'<-');
        let apply = `<td class="tbCell codblock-control bg-color text-style select-kor-font2 td-width tr-control" style="text-align: center; font-size: 0.875rem; line-height: 1.5rem; font-weight: 100; "></td>`;
        for (let k = 0; k < dividingRowCount; k++) {
            console.log(k)
            let tbRow = tbBody.children().eq(row + k);

            console.log(tbRow, '<-');
            for (let i = 0; i < dividingColCount; i++) {

                console.log("COL:", col)
                console.log(tbRow.children().eq(col - 1))
                if(col != 0) {

                    tbRow.children().eq(col - 1).after($(apply));
                } else {
                    tbRow.prepend($(apply));
                }
            }
        }

        bindEventListener();
        
    }



    function generateRows() {
        row = $(".tableRow").val();
        let tbBody = $(".tbBody");

        let apply = `<td class="tbCell codblock-control bg-color text-style select-kor-font2 td-width tr-control" style="text-align: center; font-size: 0.875rem; line-height: 1.5rem; font-weight: 100; "></td>`;

        for (let i = 0; i < column; i++) {

            let tbRow = tbBody.children().eq(i);
            tbRow.empty();

            for (let j = 0; j < row; j++) {

                tbRow.append($(apply));

            }
        }
        bindEventListener();
    }

    function generateColumns() {
        column = $(".tableColumn").val();
        let tbBody = $(".tbBody");

        tbBody.empty();

        let apply = `<td class="tbCell codblock-control bg-color text-style select-kor-font2 td-width tr-control" style="text-align: center; font-size: 0.875rem; line-height: 1.5rem; font-weight: 100; background-color: rgba(239, 244, 247, 0.97);"></td>`;

        for (let i = 0; i < column; i++) {

            let tr = `<tr></tr>`;
            tbBody.append($(tr));

            let tbRow = tbBody.children().eq(i);

            for (let j = 0; j < row; j++) {
                tbRow.append($(apply));
            }
        }

        bindEventListener();
    }


    function fillInCell(col, row) {

        let tbBody = $(".tbBody");

        let tbColumns = tbBody.children().eq(col);

        console.log('insertingRowCount, insertingColCount: ', insertingRowCount, insertingColCount)
        let apply = `<td class="tbCell merged codblock-control bg-color text-style select-kor-font2 td-width tr-control" style="text-align: center; font-size: 0.875rem; line-height: 1.5rem; font-weight: 100; background-color: rgba(239, 244, 247, 0.97);" rowspan=${insertingRowCount} colspan=${insertingColCount} ></td>`; //

        let tbRow;

        if (row == 0) {

            tbRow = tbColumns.children().eq(0);

            if (tbRow.get(0) != undefined) {
                tbRow.before($(apply));
            } else {
                tbColumns.append($(apply));
            }
        } else if (row > 0) {
            tbRow = tbColumns.children().eq(row - 1);
            tbRow.after($(apply));
        }

        // resets the count
        insertingRowCount = 0;
        insertingColCount = 0;


        bindEventListener();
        


    }

    function findAndDelete(col, row) {
        let tbBody = $(".tbBody");
        let tbColumns = tbBody.children();

        for (let i = 0; i < tbColumns.length; i++) {
            let tbRow = tbColumns[i];
            let tbRowList = $(tbRow).children();

            // console.log(`Row ${i}: `, tbRow)
            // let tbCell = $(tbRow).children().eq(row);

            for (let j = 0; j < tbRowList.length; j++) {
                let tbCell = tbRowList[j];
                // loops every table cell
                if (col == i && row == j) { // console.log(tbCell);
                    $(tbCell).remove();
                }


            }
        }
    }
