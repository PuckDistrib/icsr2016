java_import(['abstractFactory.intermediate', 'abstractFactory.intermediate.products']).

declareSet(motifProducts, ['MotifButton', 'MotifMenu']).

friendOf('AbstractFactoryDemo.motifButton', 'MotifButton').
friendOf('AbstractFactoryDemo.motifMenu', 'MotifMenu').

declareSet(windowsProducts,['WindowsButton','WindowsMenu']).
friendOf('AbstractFactoryDemo.windowsButton', 'WindowsButton').
friendOf('AbstractFactoryDemo.windowsMenu', 'WindowsMenu').


declareSetUnion(products_, [motifProducts, windowsProducts]).

hideSet(products_).

friendOf(products, products_).
