java_import(['abstractFactory.candidate']).

declareSet(motifProducts, ['MotifButton', 'MotifMenu']).

declareSet(windowsProducts,['WindowsButton','WindowsMenu']).

declareSetUnion(products, [motifProducts, windowsProducts]).

hideSet(products).

