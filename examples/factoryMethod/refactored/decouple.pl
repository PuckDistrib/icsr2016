
java_import(['factoryMethod.refactored', 'javax.swing']).

declareSet(creators, ['LabelCreatorImpl.create__void', 'ButtonCreatorImpl.create__void']).
declareSet(products, ['JLabel', 'JButton']).
friendOf('LabelCreatorImpl', 'LabelCreatorImpl.create__void').
friendOf('ButtonCreatorImpl', 'ButtonCreatorImpl.create__void').

hideSet(creators).
hideSetButFrom(products, creators).
friendOf(['javax.swing'], products).
