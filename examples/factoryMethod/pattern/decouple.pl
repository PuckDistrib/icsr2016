
java_import(['factoryMethod.pattern', 'javax.swing']).

declareSet(creators, ['LabelCreator.createComponent__void', 'ButtonCreator.createComponent__void']).
declareSet(products, ['JLabel', 'JButton']).
friendOf('LabelCreator', 'LabelCreator.createComponent__void').
friendOf('ButtonCreator', 'ButtonCreator.createComponent__void').

hideSet(creators).
hideSetButFrom(products, creators).
friendOf(['javax.swing'], products).
