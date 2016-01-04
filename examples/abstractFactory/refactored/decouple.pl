java_import(['abstractFactory.refactored', 'abstractFactory.refactored.products']).

declareSet(motifProducts, ['MotifButton', 'MotifMenu']).

friendOf('MotifFactory.motifButton', 'MotifButton').
friendOf('MotifFactory.motifMenu', 'MotifMenu').

declareSet(windowsProducts,['WindowsButton','WindowsMenu']).
friendOf('WindowsFactory.windowsButton', 'WindowsButton').
friendOf('WindowsFactory.windowsMenu', 'WindowsMenu').


declareSetUnion(products_, [motifProducts, windowsProducts]).

hideSet(products_).

friendOf(products, products_).
